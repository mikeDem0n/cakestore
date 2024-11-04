package main.java.persistence.impl;

import main.java.domain.Account;
import main.java.persistence.AccountDao;
import main.java.persistence.DBUtil;

import java.sql.*;

public abstract class AccountDaoImpl implements AccountDao {

    // SQL Queries
    private static final String GET_ACCOUNT_BY_USERNAME =
            "SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, " +
                    "ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, " +
                    "ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, " +
                    "PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, " +
                    "BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? " +
                    "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID " +
                    "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD =
            "SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, " +
                    "ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, " +
                    "ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, " +
                    "PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, " +
                    "BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? " +
                    "AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID " +
                    "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String UPDATE_ACCOUNT =
            "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, ADDR1 = ?, ADDR2 = ?, " +
                    "CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? WHERE USERID = ?";

    private static final String INSERT_ACCOUNT =
            "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PROFILE =
            "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";

    private static final String INSERT_PROFILE =
            "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";

    private static final String UPDATE_SIGNON =
            "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    private static final String INSERT_SIGNON =
            "INSERT INTO SIGNON (PASSWORD, USERNAME) VALUES (?, ?)";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = mapAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Account account = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = mapAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            setAccountParameters(preparedStatement, account);
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAccount(Account account) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT)) {
            setAccountParameters(preparedStatement, account);
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE)) {
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE)) {
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(String username, String password) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(String username, String password) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account mapAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setUsername(resultSet.getString("USERNAME"));
        account.setEmail(resultSet.getString("EMAIL"));
        account.setFirstName(resultSet.getString("FIRSTNAME"));
        account.setLastName(resultSet.getString("LASTNAME"));
        account.setStatus(resultSet.getString("STATUS"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("CITY"));
        account.setState(resultSet.getString("STATE"));
        account.setZip(resultSet.getString("ZIP"));
        account.setCountry(resultSet.getString("COUNTRY"));
        account.setPhone(resultSet.getString("PHONE"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setListOption(resultSet.getBoolean("listOption"));
        account.setBannerOption(resultSet.getBoolean("bannerOption"));
        account.setBannerName(resultSet.getString("BANNERNAME"));
        return account;
    }

    private void setAccountParameters(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setString(1, account.getEmail());
        preparedStatement.setString(2, account.getFirstName());
        preparedStatement.setString(3, account.getLastName());
        preparedStatement.setString(4, account.getStatus());
        preparedStatement.setString(5, account.getAddress1());
        preparedStatement.setString(6, account.getAddress2());
        preparedStatement.setString(7, account.getCity());
        preparedStatement.setString(8, account.getState());
        preparedStatement.setString(9, account.getZip());
        preparedStatement.setString(10, account.getCountry());
        preparedStatement.setString(11, account.getPhone());
    }
}

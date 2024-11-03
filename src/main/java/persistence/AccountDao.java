package main.java.persistence;
import main.java.domain.Account;


public interface AccountDao {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    Account getAccountByUsernameAndPassword(String username, String password);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);

    void updateSignon(String username, String password);

    void insertSignon(String username, String password);
}

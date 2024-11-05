package main.java.persistence.impl;

import main.java.domain.Cart;
import main.java.persistence.CartDao;
import main.java.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDaoImpl implements CartDao {
    @Override
    public void saveCart(Cart cart) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO cart (user_id, sub_total) VALUES (?, ?)");
            statement.setString(1, cart.getUserId());
            statement.setBigDecimal(2, cart.getSubTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateCart(Cart cart) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("UPDATE cart SET sub_total = ? WHERE user_id = ?");
            statement.setBigDecimal(1, cart.getSubTotal());
            statement.setString(2, cart.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public Cart loadCart(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Cart cart = new Cart();
        cart.setUserId(userId);

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM cart WHERE user_id = ?");
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart.setSubTotal(resultSet.getBigDecimal("sub_total"));
                // Load CartItems (implement this part in CartItemDao)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
        return cart;
    }

    @Override
    public void deleteCart(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM cart WHERE user_id = ?");
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}

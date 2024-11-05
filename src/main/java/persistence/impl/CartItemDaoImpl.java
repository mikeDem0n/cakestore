package main.java.persistence.impl;

import main.java.domain.CartItem;
import main.java.persistence.CartItemDao;
import main.java.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    @Override
    public void saveCartItem(String userId, CartItem cartItem) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO cart_item (user_id, item_id, quantity, in_stock, total) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, userId);
            statement.setString(2, cartItem.getItem().getItemId());
            statement.setInt(3, cartItem.getQuantity());
            statement.setBoolean(4, cartItem.isInStock());
            statement.setBigDecimal(5, cartItem.getTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateCartItem(String userId, CartItem cartItem) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("UPDATE cart_item SET quantity = ?, in_stock = ?, total = ? WHERE user_id = ? AND item_id = ?");
            statement.setInt(1, cartItem.getQuantity());
            statement.setBoolean(2, cartItem.isInStock());
            statement.setBigDecimal(3, cartItem.getTotal());
            statement.setString(4, userId);
            statement.setString(5, cartItem.getItem().getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteCartItem(String userId, String itemId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM cart_item WHERE user_id = ? AND item_id = ?");
            statement.setString(1, userId);
            statement.setString(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public List<CartItem> loadCartItems(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CartItem> cartItems = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM cart_item WHERE user_id = ?");
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CartItem cartItem = new CartItem();
                // Populate cartItem fields from resultSet
                // cartItem.setItem(...);
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setInStock(resultSet.getBoolean("in_stock"));
                cartItem.setTotal(resultSet.getBigDecimal("total"));
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
        return cartItems;
    }
}

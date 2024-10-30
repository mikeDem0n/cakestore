package main.java.persistence.impl;

import main.java.domain.Item;
import main.java.persistence.DBUtil;
import main.java.persistence.ItemDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    private static final String UPDATE_INVENTORY_QUANTITY_SQL =
            "UPDATE inventory SET qty = ? WHERE itemid = ?";
    private static final String GET_INVENTORY_QUANTITY_SQL =
            "SELECT qty FROM inventory WHERE itemid = ?";
    private static final String GET_ITEM_LIST_BY_PRODUCT_SQL =
            "SELECT i.itemid, i.productid, i.listprice, i.unitcost, i.supplierid, i.status, i.attr1, i.attr2, i.attr3, i.attr4, i.attr5, p.productid " +
                    "FROM item i JOIN product p ON i.productid = p.productid WHERE i.productid = ?";
    private static final String GET_ITEM_SQL =
            "SELECT itemid, productid, listprice, unitcost, supplierid, status, attr1, attr2, attr3, attr4, attr5 FROM item WHERE itemid = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        String itemId = (String) param.get("itemId");
        int quantity = (int) param.get("quantity");
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY_SQL)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int quantity = 0;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            quantity = resultSet.getInt("qty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString("itemid"));
                item.setProductId(resultSet.getString("productid"));
                item.setListPrice(resultSet.getBigDecimal("listprice"));
                item.setUnitCost(resultSet.getBigDecimal("unitcost"));
                item.setSupplierId(resultSet.getInt("supplierid"));
                item.setStatus(resultSet.getString("status"));
                item.setAttribute1(resultSet.getString("attr1"));
                item.setAttribute2(resultSet.getString("attr2"));
                item.setAttribute3(resultSet.getString("attr3"));
                item.setAttribute4(resultSet.getString("attr4"));
                item.setAttribute5(resultSet.getString("attr5"));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            item = new Item();
            item.setItemId(resultSet.getString("itemid"));
            item.setProductId(resultSet.getString("productid"));
            item.setListPrice(resultSet.getBigDecimal("listprice"));
            item.setUnitCost(resultSet.getBigDecimal("unitcost"));
            item.setSupplierId(resultSet.getInt("supplierid"));
            item.setStatus(resultSet.getString("status"));
            item.setAttribute1(resultSet.getString("attr1"));
            item.setAttribute2(resultSet.getString("attr2"));
            item.setAttribute3(resultSet.getString("attr3"));
            item.setAttribute4(resultSet.getString("attr4"));
            item.setAttribute5(resultSet.getString("attr5"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}

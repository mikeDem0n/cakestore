package main.java.persistence.impl;

import main.java.domain.Item;
import main.java.persistence.ItemDao;

import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {

    }

    @Override
    public int getInventoryQuantity(String itemId) {
        return 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return List.of();
    }

    @Override
    public Item getItem(String itemId) {
        return null;
    }
}

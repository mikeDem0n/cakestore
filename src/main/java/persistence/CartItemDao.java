package main.java.persistence;

import main.java.domain.CartItem;

import java.util.List;

public interface CartItemDao {
    void saveCartItem(String userId, CartItem cartItem);
    void updateCartItem(String userId, CartItem cartItem);
    void deleteCartItem(String userId, String itemId);
    List<CartItem> loadCartItems(String userId);
}

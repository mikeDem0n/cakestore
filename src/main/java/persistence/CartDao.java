package main.java.persistence;

import main.java.domain.Cart;

public interface CartDao {
    void saveCart(Cart cart);
    void updateCart(Cart cart);
    Cart loadCart(String userId);
    void deleteCart(String userId);
}

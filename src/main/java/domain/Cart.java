package main.java.domain;

import main.java.persistence.CartDao;
import main.java.persistence.CartItemDao;
import main.java.persistence.impl.CartDaoImpl;
import main.java.persistence.impl.CartItemDaoImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {

    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<>());
    private final List<CartItem> itemList = new ArrayList<>();
    private String userId;
    private BigDecimal subTotal;

    private final CartDao cartDao = new CartDaoImpl();
    private final CartItemDao cartItemDao = new CartItemDaoImpl();

    public Cart() {
        this.subTotal = BigDecimal.ZERO;
    }

    public Cart(String userId) {
        this.userId = userId;
        this.subTotal = BigDecimal.ZERO;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
            cartItemDao.saveCartItem(userId, cartItem);
        } else {
            cartItem.incrementQuantity();
            cartItemDao.updateCartItem(userId, cartItem);
        }
        cartDao.updateCart(this);
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            itemList.remove(cartItem);
            cartItemDao.deleteCartItem(userId, itemId);
            cartDao.updateCart(this);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = itemMap.get(itemId);
        if (cartItem != null) {
            cartItem.incrementQuantity();
            cartItemDao.updateCartItem(userId, cartItem);
            cartDao.updateCart(this);
        }
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = itemMap.get(itemId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItemDao.updateCartItem(userId, cartItem);
            cartDao.updateCart(this);
        }
    }

    public BigDecimal calculateSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (CartItem cartItem : itemList) {
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    public void loadFromDatabase() {
        Cart persistedCart = cartDao.loadCart(userId);
        if (persistedCart != null) {
            itemList.clear();
            itemMap.clear();
            itemList.addAll(cartItemDao.loadCartItems(userId));
            for (CartItem item : itemList) {
                itemMap.put(item.getItem().getItemId(), item);
            }
            this.subTotal = persistedCart.getSubTotal();
        }
    }
}

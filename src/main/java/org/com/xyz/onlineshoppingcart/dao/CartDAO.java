package org.com.xyz.onlineshoppingcart.dao;

import org.com.xyz.onlineshoppingcart.model.CartItem;

import java.util.List;

public interface CartDAO {
    CartItem save(CartItem entity);
    CartItem getCartItem(String cartItemId);
    List<CartItem> getAllCartItems();
    CartItem deleteCartItem(String cartItemId);
}
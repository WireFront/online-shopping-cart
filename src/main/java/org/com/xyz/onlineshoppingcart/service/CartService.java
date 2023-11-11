package org.com.xyz.onlineshoppingcart.service;

import org.com.xyz.onlineshoppingcart.result.CartItemResult;
import org.com.xyz.onlineshoppingcart.result.CartResult;

public interface CartService {
    public CartItemResult addToCart(String item, int qty);
    public CartResult fetchCart();
    public CartItemResult removeFromCart(String cartItemId);
}
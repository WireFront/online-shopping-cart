package org.com.xyz.onlineshoppingcart.dao.impl;

import org.com.xyz.onlineshoppingcart.dao.CartDAO;
import org.com.xyz.onlineshoppingcart.model.CartItem;
import org.com.xyz.onlineshoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItem(String cartItemId) {
        return cartRepository.findById(cartItemId).orElse(null);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem deleteCartItem(String cartItemId) {
        CartItem cartItem = cartRepository.findById(cartItemId).orElse(null);
        cartRepository.delete(cartItem);
        return cartItem;
    }
}
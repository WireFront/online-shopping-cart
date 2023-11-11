package org.com.xyz.onlineshoppingcart.service.impl;

import org.com.xyz.onlineshoppingcart.dao.CartDAO;
import org.com.xyz.onlineshoppingcart.dao.ProductDAO;
import org.com.xyz.onlineshoppingcart.model.CartItem;
import org.com.xyz.onlineshoppingcart.model.Product;
import org.com.xyz.onlineshoppingcart.result.CartItemResult;
import org.com.xyz.onlineshoppingcart.result.CartResult;
import org.com.xyz.onlineshoppingcart.service.CartService;
import org.com.xyz.onlineshoppingcart.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public CartItemResult addToCart(String productId, int qty) {
        AssertUtil.isBlank(productId, "ILLEGAL_PARAM", "blank productId");
        Product product = productDAO.getProduct(productId);
        AssertUtil.isNull(product, "ILLEGAL_PARAM", "product does not exist");

        List<CartItem> items = cartDAO.getAllCartItems();
        // Check if the product is already in the cart
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getName().equals(product.getName())) {
                // If the product is already in the cart, add the quantity
                cartItem.setQuantity(cartItem.getQuantity() + qty);
                cartDAO.save(cartItem);

                CartItemResult result = new CartItemResult();
                result.setName(cartItem.getProduct().getName());
                result.setPrice(String.valueOf(cartItem.getProduct().getPrice()));
                result.setQuantity(String.valueOf(cartItem.getQuantity()));
                result.setMessage("Item is already in the cart, added quantity instead");
                return result;
            }
        }

        CartItem cartItem = cartDAO.save(CartItem.builder()
                .product(product)
                .quantity(qty)
                .build());

        return buildCartItemResult(cartItem, "Item added to cart");
    }

    @Override
    public CartResult fetchCart() {
        List<CartItem> items = cartDAO.getAllCartItems();
        CartResult result = new CartResult();
        result.setCartItems(items);
        result.setMessage("Display cart items");
        return result;
    }

    @Override
    public CartItemResult removeFromCart(String cartItemId) {
        AssertUtil.isBlank(cartItemId, "ILLEGAL_PARAM", "blank cartItemId");
        CartItem cartItem = cartDAO.deleteCartItem(cartItemId);
        return buildCartItemResult(cartItem, "Item removed from the cart");
    }

    private CartItemResult buildCartItemResult(CartItem cartItem, String message) {
        CartItemResult result = new CartItemResult();
        result.setName(cartItem.getProduct().getName());
        result.setPrice(String.valueOf(cartItem.getProduct().getPrice()));
        result.setQuantity(String.valueOf(cartItem.getQuantity()));
        result.setMessage("Item removed from the cart");
        return result;
    }
}
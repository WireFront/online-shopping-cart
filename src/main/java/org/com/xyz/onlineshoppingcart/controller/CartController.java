package org.com.xyz.onlineshoppingcart.controller;

import org.com.xyz.onlineshoppingcart.exception.CartException;
import org.com.xyz.onlineshoppingcart.result.CartItemResult;
import org.com.xyz.onlineshoppingcart.result.CartResult;
import org.com.xyz.onlineshoppingcart.result.ProductResult;
import org.com.xyz.onlineshoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<CartResult> fetchCart() {
        CartResult result = null;
        try {
            result = cartService.fetchCart();
        } catch (CartException ce) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ce.getMessage(), ce);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/add/")
    public ResponseEntity<CartItemResult> addToCart(@RequestParam String productId, @RequestParam int qty) {
        CartItemResult result = null;
        try {
            result = cartService.addToCart(productId, qty);
        } catch (CartException ce) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ce.getMessage(), ce);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<CartItemResult> removeFromCart(@PathVariable String cartItemId) {
        CartItemResult result = null;
        try {
            result = cartService.removeFromCart(cartItemId);
        } catch (CartException ce) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ce.getMessage(), ce);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public String checkout() {
        // Implement order processing logic here
        // cart.clear();
        return "Order placed successfully";
    }

}
package org.com.xyz.onlineshoppingcart.dao;

import org.com.xyz.onlineshoppingcart.model.Product;

import java.util.List;

public interface ProductDAO {
    Product save(Product entity);
    List<Product> getAllProducts();
    Product getProduct(String productId);
    Product getProductByName(String productName);
    Product deleteProduct(String productId);
}
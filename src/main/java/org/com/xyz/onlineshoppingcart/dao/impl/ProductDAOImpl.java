package org.com.xyz.onlineshoppingcart.dao.impl;

import org.com.xyz.onlineshoppingcart.dao.ProductDAO;
import org.com.xyz.onlineshoppingcart.model.Product;
import org.com.xyz.onlineshoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.findByName(productName).orElse(null);
    }

    @Override
    public Product deleteProduct(String productId) {
        Product productEntity = productRepository.findById(productId).orElse(null);
        productRepository.delete(productEntity);
        return productEntity;
    }
}
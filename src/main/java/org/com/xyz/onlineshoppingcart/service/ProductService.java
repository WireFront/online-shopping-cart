package org.com.xyz.onlineshoppingcart.service;

import org.com.xyz.onlineshoppingcart.request.ProductRequest;
import org.com.xyz.onlineshoppingcart.result.CartResult;
import org.com.xyz.onlineshoppingcart.result.ProductResult;

public interface ProductService {
    public ProductResult createProduct(ProductRequest productRequest);
}
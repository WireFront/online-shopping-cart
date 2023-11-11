package org.com.xyz.onlineshoppingcart.service.impl;

import org.com.xyz.onlineshoppingcart.dao.ProductDAO;
import org.com.xyz.onlineshoppingcart.model.Product;
import org.com.xyz.onlineshoppingcart.request.ProductRequest;
import org.com.xyz.onlineshoppingcart.result.ProductResult;
import org.com.xyz.onlineshoppingcart.service.ProductService;
import org.com.xyz.onlineshoppingcart.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public ProductResult createProduct(ProductRequest productRequest) {
        AssertUtil.isNull(productRequest, "ILLEGAL_PARAM", "null productRequest");
        AssertUtil.isNotNull(productDAO.getProductByName(productRequest.getName()), "PRODUCT_EXISTS", "product already exists");

        Product product = productDAO.save(Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build());

        ProductResult result = new ProductResult();
        result.setName(product.getName());
        result.setPrice(String.valueOf(product.getPrice()));
        result.setId(String.valueOf(product.getId()));
        result.setMessage("Created product successfully");
        return result;
    }
}
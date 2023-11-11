package org.com.xyz.onlineshoppingcart.controller;

import org.com.xyz.onlineshoppingcart.exception.CartException;
import org.com.xyz.onlineshoppingcart.request.ProductRequest;
import org.com.xyz.onlineshoppingcart.result.ProductResult;
import org.com.xyz.onlineshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResult> createProduct(@RequestHeader(required = false, value = "Authorization") String authorizationHeader,
                                                       @RequestBody ProductRequest productRequest) {
        // Check if Authorization header is present
        if (null == authorizationHeader || !authorizationHeader.startsWith("Basic ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        // Extract and decode the username and password from the Authorization header
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] parts = credentials.split(":", 2);
        String username = parts[0];
        String password = parts[1];

        if (!("user".equals(username) && "password".equals(password))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        ProductResult result = null;
        try {
            result = productService.createProduct(productRequest);
        } catch (CartException ce) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ce.getMessage(), ce);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/fetchDummyProducts")
    public ResponseEntity<String> getDummyProducts() {
        RestTemplate restTemplate = new RestTemplate();

        // Define the endpoint URL
        String url = "https://dummyjson.com/products";

        // Make a GET request and retrieve the response
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // Check the HTTP status code
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Successful response
            String responseBody = responseEntity.getBody();
            System.out.println("Response Body:\n" + responseBody);
        } else {
            // Handle error response
            System.out.println("Error Response: " + responseEntity.getStatusCode());
        }
        return responseEntity;
    }
}
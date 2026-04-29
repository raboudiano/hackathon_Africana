package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductByID(Long id);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> getAllById(List<Long> ids);
}

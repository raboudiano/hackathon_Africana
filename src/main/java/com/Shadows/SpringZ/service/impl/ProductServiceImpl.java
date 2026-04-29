package com.Shadows.SpringZ.service.impl;

import com.Shadows.SpringZ.model.Product;
import com.Shadows.SpringZ.repository.ProductRepository;
import com.Shadows.SpringZ.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByID(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: id=" + id));
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}


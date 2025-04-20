package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(String id, Product product) {
        return productRepository.findById(id).map(existing -> {
            Product updated = new Product(id, product.name(), product.price(), product.description(), product.imgUrl(), product.productDetail());
            return productRepository.save(updated);
        });
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}

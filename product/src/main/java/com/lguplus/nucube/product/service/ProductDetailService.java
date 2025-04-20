package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.domain.ProductDetail;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {

    private final ProductRepository productRepository;

    public ProductDetailService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> addReview(String productId, String review) {
        return productRepository.findById(productId).map(product -> {
            ProductDetail detail = product.productDetail();
            List<String> reviews = new ArrayList<>(detail.reviewList());
            reviews.add(review);
            ProductDetail updatedDetail = new ProductDetail(detail.id(), detail.like(), reviews, detail.content());
            Product updatedProduct = new Product(product.id(), product.name(), product.price(), product.description(), product.imgUrl(), updatedDetail);
            return productRepository.save(updatedProduct);
        });
    }

    public Optional<Product> likeProduct(String productId) {
        return productRepository.findById(productId).map(product -> {
            ProductDetail detail = product.productDetail();
            int likeCount = Integer.parseInt(detail.like());
            likeCount++;
            ProductDetail updatedDetail = new ProductDetail(detail.id(), String.valueOf(likeCount), detail.reviewList(), detail.content());
            Product updatedProduct = new Product(product.id(), product.name(), product.price(), product.description(), product.imgUrl(), updatedDetail);
            return productRepository.save(updatedProduct);
        });
    }
}
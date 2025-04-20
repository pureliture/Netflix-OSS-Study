package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.domain.ProductDetail;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductDetailServiceTest {

    private ProductRepository productRepository;
    private ProductDetailService productDetailService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productDetailService = new ProductDetailService(productRepository);
    }

    @Test
    void 리뷰_추가_성공() {
        ProductDetail detail = new ProductDetail("d1", "0", List.of(), "내용");
        Product product = new Product("p1", "name", 1000, "", "", detail);

        when(productRepository.findById("p1")).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Product> updated = productDetailService.addReview("p1", "좋아요!");
        assertThat(updated).isPresent();
        assertThat(updated.get().productDetail().reviewList()).contains("좋아요!");
    }

    @Test
    void 좋아요_증가() {
        ProductDetail detail = new ProductDetail("d1", "3", List.of(), "내용");
        Product product = new Product("p1", "name", 1000, "", "", detail);

        when(productRepository.findById("p1")).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Product> liked = productDetailService.likeProduct("p1");
        assertThat(liked).isPresent();
        assertThat(liked.get().productDetail().like()).isEqualTo("4");
    }
}
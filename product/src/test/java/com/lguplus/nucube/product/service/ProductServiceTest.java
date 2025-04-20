package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.domain.ProductDetail;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void 상품_등록_성공() {
        Product product = new Product("1", "Test", 1000, "desc", "url", null);
        when(productRepository.save(any())).thenReturn(product);

        Product saved = productService.createProduct(product);
        assertThat(saved.name()).isEqualTo("Test");
    }

    @Test
    void 상품_전체조회() {
        when(productRepository.findAll()).thenReturn(List.of(new Product("1", "Test", 1000, "", "", null)));
        List<Product> products = productService.getAllProducts();
        assertThat(products).hasSize(1);
    }

    @Test
    void 상품_조회_성공() {
        Product product = new Product("1", "Test", 1000, "", "", null);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Optional<Product> found = productService.getProductById("1");
        assertThat(found).isPresent();
        assertThat(found.get().name()).isEqualTo("Test");
    }
}
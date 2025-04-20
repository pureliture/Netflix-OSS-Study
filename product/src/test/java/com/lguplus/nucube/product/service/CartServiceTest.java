package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Cart;
import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.repository.CartRepository;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private CartService cartService;

    @BeforeEach
    void setup() {
        cartRepository = mock(CartRepository.class);
        productRepository = mock(ProductRepository.class);
        cartService = new CartService(cartRepository, productRepository);
    }

    @Test
    void 장바구니_상품_추가() {
        Cart cart = new Cart("c1", "myCart", List.of());
        Product product = new Product("p1", "상품", 1000, "", "", null);

        when(productRepository.findById("p1")).thenReturn(Optional.of(product));
        when(cartRepository.findById("c1")).thenReturn(Optional.of(cart));
        when(cartRepository.save(any())).thenReturn(new Cart("c1", "myCart", List.of(product)));

        Optional<Cart> updatedCart = cartService.addProductToCart("c1", "p1");

        assertThat(updatedCart).isPresent();
        assertThat(updatedCart.get().productList()).hasSize(1);
    }
}

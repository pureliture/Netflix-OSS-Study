package com.lguplus.nucube.product.service;

import com.lguplus.nucube.product.domain.Cart;
import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.repository.CartRepository;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> addProductToCart(String cartId, String productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) return Optional.empty();

        return cartRepository.findById(cartId).map(cart -> {
            List<Product> updatedList = new ArrayList<>(cart.productList());
            updatedList.add(productOpt.get());
            Cart updatedCart = new Cart(cart.id(), cart.name(), updatedList);
            return cartRepository.save(updatedCart);
        });
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
}
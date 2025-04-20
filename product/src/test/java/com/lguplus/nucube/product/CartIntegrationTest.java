package com.lguplus.nucube.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.nucube.product.domain.Cart;
import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.domain.ProductDetail;
import com.lguplus.nucube.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 장바구니에_상품_추가() throws Exception {
        // 1. 상품 저장
        ProductDetail detail = new ProductDetail("d2", "0", List.of(), "내용");
        Product saved = productRepository.save(new Product(null, "P2", 2000, "desc", "img", detail));

        // 2. 장바구니 생성
        Cart cart = new Cart(null, "myCart", List.of());
        String cartRes = mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cart)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Cart createdCart = objectMapper.readValue(cartRes, Cart.class);

        // 3. 장바구니에 상품 추가
        mockMvc.perform(post("/carts/" + createdCart.id() + "/products/" + saved.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productList[0].name").value("P2"));
    }
}
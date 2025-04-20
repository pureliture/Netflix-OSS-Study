package com.lguplus.nucube.product.controller;

import com.lguplus.nucube.product.domain.Cart;
import com.lguplus.nucube.product.dto.CartDTO;
import com.lguplus.nucube.product.dto.DtoMapper;
import com.lguplus.nucube.product.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@Tag(name = "장바구니", description = "장바구니 관련 API")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "장바구니 생성", description = "새로운 장바구니를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "생성된 장바구니 반환")
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO) {
        Cart cart = DtoMapper.toCart(cartDTO);
        return ResponseEntity.ok(cartService.createCart(cart));
    }

    @Operation(summary = "장바구니 조회", description = "장바구니 ID로 장바구니를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "장바구니 정보 반환")
    @ApiResponse(responseCode = "404", description = "장바구니가 존재하지 않음")
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(
            @Parameter(description = "장바구니 ID", required = true) @PathVariable String id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "장바구니에 상품 추가", description = "장바구니에 특정 상품을 추가합니다.")
    @ApiResponse(responseCode = "200", description = "상품이 추가된 장바구니 반환")
    @ApiResponse(responseCode = "404", description = "상품 또는 장바구니가 존재하지 않음")
    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @Parameter(description = "장바구니 ID", required = true) @PathVariable String cartId,
            @Parameter(description = "상품 ID", required = true) @PathVariable String productId) {
        return cartService.addProductToCart(cartId, productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "장바구니 삭제", description = "장바구니를 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "삭제 완료")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(
            @Parameter(description = "장바구니 ID", required = true) @PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}

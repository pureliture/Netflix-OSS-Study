package com.lguplus.nucube.product.controller;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.service.ProductDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "상품 상세", description = "상품의 상세정보 및 리뷰 관련 API")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @Operation(
            summary = "리뷰 추가",
            description = "해당 상품에 리뷰를 추가합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "추가할 리뷰 텍스트",
                    required = true,
                    content = @Content(schema = @Schema(type = "string", example = "이 상품 정말 좋아요!"))
            )
    )
    @ApiResponse(responseCode = "200", description = "리뷰 추가된 상품 반환")
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않음")
    @PostMapping("/{id}/reviews")
    public ResponseEntity<Product> addReview(
            @Parameter(description = "상품 ID", required = true) @PathVariable String id,
            @RequestBody String review) {
        return productDetailService.addReview(id, review)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "좋아요 추가",
            description = "상품에 좋아요를 추가합니다. 좋아요 수는 문자열로 저장됩니다."
    )
    @ApiResponse(responseCode = "200", description = "좋아요 증가된 상품 반환")
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않음")
    @PostMapping("/{id}/like")
    public ResponseEntity<Product> likeProduct(
            @Parameter(description = "상품 ID", required = true) @PathVariable String id) {
        return productDetailService.likeProduct(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

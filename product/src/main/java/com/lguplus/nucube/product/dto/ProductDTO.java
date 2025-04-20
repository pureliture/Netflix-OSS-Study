package com.lguplus.nucube.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 DTO")
public record ProductDTO(
        @Schema(description = "상품명", example = "iPhone 15") String name,
        @Schema(description = "가격", example = "1500000") int price,
        @Schema(description = "상품 설명", example = "최신형 아이폰") String description,
        @Schema(description = "이미지 URL", example = "https://example.com/image.jpg") String imgUrl,
        @Schema(description = "상세 정보") ProductDetailDTO productDetail
) {
}
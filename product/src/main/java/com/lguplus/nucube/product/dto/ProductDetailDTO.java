package com.lguplus.nucube.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "상품 상세 정보 DTO")
public record ProductDetailDTO(
        @Schema(description = "좋아요 수", example = "10") String like,
        @Schema(description = "리뷰 목록") List<String> reviewList,
        @Schema(description = "상세 설명", example = "고급형 스마트폰입니다.") String content
) {
}
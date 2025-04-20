package com.lguplus.nucube.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "장바구니 DTO")
public record CartDTO(
        @Schema(description = "장바구니 이름", example = "user123-cart") String name
) {
}
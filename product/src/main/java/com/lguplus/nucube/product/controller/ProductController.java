package com.lguplus.nucube.product.controller;

import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.dto.DtoMapper;
import com.lguplus.nucube.product.dto.ProductDTO;
import com.lguplus.nucube.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "상품", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "상품 등록", description = "새로운 상품을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "등록된 상품 정보 반환")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = DtoMapper.toProduct(productDTO);
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @Operation(summary = "전체 상품 조회", description = "등록된 모든 상품을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 목록 반환")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "상품 상세 조회", description = "상품 ID로 특정 상품을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 정보 반환")
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않음")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "상품 ID", required = true) @PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "상품 수정", description = "상품 ID로 기존 상품 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "수정된 상품 반환")
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않음")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "상품 ID", required = true) @PathVariable String id,
            @RequestBody ProductDTO productDTO) {
        Product updatedProduct = DtoMapper.toProduct(productDTO);
        return productService.updateProduct(id, updatedProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "상품 삭제", description = "상품 ID로 상품을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "삭제 성공")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "상품 ID", required = true) @PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

package com.lguplus.nucube.product.dto;

import com.lguplus.nucube.product.domain.Cart;
import com.lguplus.nucube.product.domain.Product;
import com.lguplus.nucube.product.domain.ProductDetail;

import java.util.List;

public class DtoMapper {

    public static Product toProduct(ProductDTO dto) {
        ProductDetailDTO d = dto.productDetail();
        ProductDetail detail = new ProductDetail(null, d.like(), d.reviewList(), d.content());
        return new Product(null, dto.name(), dto.price(), dto.description(), dto.imgUrl(), detail);
    }

    public static Cart toCart(CartDTO dto) {
        return new Cart(null, dto.name(), List.of());
    }
}
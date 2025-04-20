package com.lguplus.nucube.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public record Cart(
        @Id
        String id,
        String name,

        List<Product> productList
) {

        public void addProduct(Product product) {
                productList.add(product);
        }

        public void addProductList(List<Product> productList) {
                productList.addAll(productList);
        }
}

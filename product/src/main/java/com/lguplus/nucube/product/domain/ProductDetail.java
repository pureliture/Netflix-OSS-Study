package com.lguplus.nucube.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public record ProductDetail(
        @Id
        String id,
        String like,
        List<String> reviewList,
        String content
) {
}

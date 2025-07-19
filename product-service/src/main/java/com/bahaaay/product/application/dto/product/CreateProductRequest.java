package com.bahaaay.product.application.dto.product;

public record CreateProductRequest(
        String name,
        String description,
        String category
) {
}

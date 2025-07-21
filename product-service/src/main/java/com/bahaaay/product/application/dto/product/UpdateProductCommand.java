package com.bahaaay.product.application.dto.product;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;

import java.math.BigDecimal;

public record UpdateProductCommand(
        ProductId productId,
        String name,
        String description,
        String category,
        BigDecimal price
) {
}

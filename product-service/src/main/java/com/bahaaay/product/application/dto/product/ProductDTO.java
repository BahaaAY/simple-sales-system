package com.bahaaay.product.application.dto.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        String description,
        String category,
        BigDecimal price,
        Instant createdAt,
        Instant updatedAt
) {
}

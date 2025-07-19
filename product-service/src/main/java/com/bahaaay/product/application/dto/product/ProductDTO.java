package com.bahaaay.product.application.dto.product;

import java.time.Instant;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        String description,
        String category,
        Instant createdAt,
        Instant updatedAt
) {
}

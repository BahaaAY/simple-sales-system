package com.bahaaay.sales.application.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record SaleTransactionDTO (
    UUID id,
    UUID saleId,
    UUID productId,
    String productName,
    BigDecimal price,
    int quantity,
    Instant createdAt,
    Instant updatedAt
) {
}

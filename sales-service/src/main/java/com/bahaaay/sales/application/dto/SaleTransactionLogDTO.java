package com.bahaaay.sales.application.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record SaleTransactionLogDTO (
        UUID id,
        UUID saleId,
        UUID transactionId,
        int oldQuantity,
        int newQuantity,
        Instant updatedAt
) {
}

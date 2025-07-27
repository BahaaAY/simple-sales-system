package com.bahaaay.sales.application.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record SaleDTO(
        UUID id,
        UUID clientId,
        String seller,
        List<SaleTransactionDTO> transactions,
        BigDecimal total,
        Instant createdAt,
        Instant updatedAt
) {

}

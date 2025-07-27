package com.bahaaay.sales.application.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateSaleTransactionRequest(
        @NotNull
        UUID productId,
        @Positive
        int quantity
) {
}

package com.bahaaay.sales.application.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UpdateSaleTransactionRequest(
        @NotNull UUID transactionId,
        @Positive int newQuantity
) {
}

package com.bahaaay.sales.application.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateSaleRequest(
        @NotNull
        UUID clientId,
        @NotBlank
        String seller,
        @NotEmpty
        List<@Valid CreateSaleTransactionRequest> transactions
) {
}

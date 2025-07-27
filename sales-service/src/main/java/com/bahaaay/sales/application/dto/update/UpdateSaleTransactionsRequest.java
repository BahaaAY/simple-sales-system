package com.bahaaay.sales.application.dto.update;

import jakarta.validation.Valid;

import java.util.List;

public record UpdateSaleTransactionsRequest(
        List<@Valid UpdateSaleTransactionRequest> transactions
) {
}

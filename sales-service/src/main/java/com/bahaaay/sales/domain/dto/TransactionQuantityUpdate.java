package com.bahaaay.sales.domain.dto;

import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;

public record TransactionQuantityUpdate(
        SaleTransactionId transactionId,
        int oldQuantity,
        int newQuantity
) {
}

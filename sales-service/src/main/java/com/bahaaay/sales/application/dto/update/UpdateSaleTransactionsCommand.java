package com.bahaaay.sales.application.dto.update;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;

import java.util.List;

public record UpdateSaleTransactionsCommand(
        SaleId saleId,
        List<UpdateSaleTransactionRequest> transactionRequestList
) {
}

package com.bahaaay.sales.application.mapper;

import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.SaleTransactionDTO;
import com.bahaaay.sales.application.dto.SaleTransactionLogDTO;
import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.entity.sales.SaleTransaction;
import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;
import org.springframework.stereotype.Component;

@Component
public class SalesDataMapper {

    public SaleDTO saleToSaleDTO(Sale sale)
    {
        if (sale == null) {
            return null;
        }

        return SaleDTO.builder()
                .id(sale.getId().getValue())
                .clientId(sale.getClientId().getValue())
                .seller(sale.getSeller())
                .transactions(sale.getTransactions().stream()
                        .map(this::saleTransactionToSaleTransactionDTO)
                        .toList())
                .total(sale.total())
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .build();
    }

    private SaleTransactionDTO saleTransactionToSaleTransactionDTO(SaleTransaction saleTransaction) {
        return SaleTransactionDTO.builder()
                .id(saleTransaction.getId().getValue())
                .saleId(saleTransaction.getSaleId().getValue())
                .productId(saleTransaction.getProductId().getValue())
                .productName(saleTransaction.getProductName())
                .quantity(saleTransaction.getQuantity())
                .price(saleTransaction.getPrice())
                .createdAt(saleTransaction.getCreatedAt())
                .updatedAt(saleTransaction.getUpdatedAt())
                .build();
    }

    public SaleTransactionLogDTO saleTransactionUpdateLogToSaleTransactionLogDTO(SaleTransactionUpdateLog saleTransactionUpdateLog) {
        if (saleTransactionUpdateLog == null) {
            return null;
        }

        return SaleTransactionLogDTO.builder()
                .id(saleTransactionUpdateLog.getId().getValue())
                .saleId(saleTransactionUpdateLog.getSaleId().getValue())
                .transactionId(saleTransactionUpdateLog.getTransactionId().getValue())
                .oldQuantity(saleTransactionUpdateLog.getOldQuantity())
                .newQuantity(saleTransactionUpdateLog.getNewQuantity())
                .updatedAt(saleTransactionUpdateLog.getUpdatedAt())
                .build();
    }
}

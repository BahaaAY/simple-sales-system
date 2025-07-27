package com.bahaaay.sales.infrastructure.persistence.mapper.sales;

import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;
import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleTransactionUpdateLogJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SalesLogsPersistenceMapper {

    public SaleTransactionUpdateLogJpaEntity saleTransactionUpdateLogToSaleTransactionUpdateLogJpaEntity(
            SaleTransactionUpdateLog saleTransactionUpdateLog
    ) {
        if (saleTransactionUpdateLog == null) {
            return null;
        }
        return SaleTransactionUpdateLogJpaEntity.builder()
                .id(saleTransactionUpdateLog.getId().getValue())
                .saleId(saleTransactionUpdateLog.getSaleId().getValue())
                .transactionId(saleTransactionUpdateLog.getTransactionId().getValue())
                .oldQuantity(saleTransactionUpdateLog.getOldQuantity())
                .newQuantity(saleTransactionUpdateLog.getNewQuantity())
                .updatedAt(saleTransactionUpdateLog.getUpdatedAt())
                .build();
    }
}

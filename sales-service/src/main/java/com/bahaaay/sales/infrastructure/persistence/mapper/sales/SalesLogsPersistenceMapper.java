package com.bahaaay.sales.infrastructure.persistence.mapper.sales;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionQuantityUpdateLogId;
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

    public SaleTransactionUpdateLog saleTransactionUpdateLogJpaEntityToSaleTransactionUpdateLog(SaleTransactionUpdateLogJpaEntity saleTransactionUpdateLogJpaEntity) {
        if (saleTransactionUpdateLogJpaEntity == null) {
            return null;
        }
        return SaleTransactionUpdateLog.load(
                SaleTransactionQuantityUpdateLogId.from(saleTransactionUpdateLogJpaEntity.getId()),
                SaleId.from(saleTransactionUpdateLogJpaEntity.getSaleId()),
                SaleTransactionId.from(saleTransactionUpdateLogJpaEntity.getTransactionId()),
                saleTransactionUpdateLogJpaEntity.getOldQuantity(),
                saleTransactionUpdateLogJpaEntity.getNewQuantity(),
                saleTransactionUpdateLogJpaEntity.getUpdatedAt()
        );
    }
}

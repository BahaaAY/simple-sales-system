package com.bahaaay.sales.infrastructure.persistence.mapper.sales;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;
import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.entity.sales.SaleTransaction;
import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleJpaEntity;
import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleTransactionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SalesPersistenceMapper {

    public Sale saleJpaEntityToSale(SaleJpaEntity saleJpaEntity)
    {
        if (saleJpaEntity == null) {
            return null;
        }
        return Sale.load(
                SaleId.from(saleJpaEntity.getId()),
                ClientId.from(saleJpaEntity.getClientId()),
                saleJpaEntity.getSeller(),
                saleJpaEntity.getTransactions().stream()
                        .map(saleTransactionJpaEntity -> saleTransactionJpaEntityToSaleTransaction(saleTransactionJpaEntity, SaleId.from(saleJpaEntity.getId())))
                        .toList(),
                saleJpaEntity.getCreatedAt(),
                saleJpaEntity.getUpdatedAt()
        );
    }

    private SaleTransaction saleTransactionJpaEntityToSaleTransaction(SaleTransactionJpaEntity saleTransactionJpaEntity, SaleId saleId) {
        if (saleTransactionJpaEntity == null) {
            return null;
        }
        return SaleTransaction.load(
                SaleTransactionId.from(saleTransactionJpaEntity.getId()),
                saleId,
                ProductId.from(saleTransactionJpaEntity.getProductId()),
                saleTransactionJpaEntity.getProductName(),
                saleTransactionJpaEntity.getQuantity(),
                saleTransactionJpaEntity.getPrice(),
                saleTransactionJpaEntity.getCreatedAt(),
                saleTransactionJpaEntity.getUpdatedAt()
        );
    }

    public SaleJpaEntity saleToSaleJpaEntity(Sale sale) {
        return SaleJpaEntity.builder()
                .id(sale.getId().getValue())
                .clientId(sale.getClientId().getValue())
                .seller(sale.getSeller())
                .transactions(sale.getTransactions().stream()
                        .map(this::saleTransactionToSaleTransactionJpaEntity)
                        .toList())
                .total(sale.total())
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .build();
    }

    private SaleTransactionJpaEntity saleTransactionToSaleTransactionJpaEntity(SaleTransaction saleTransaction) {
        return SaleTransactionJpaEntity.builder()
                .id(saleTransaction.getId().getValue())
                .sale(SaleJpaEntity.builder()
                        .id(saleTransaction.getSaleId().getValue())
                        .build())
                .productId(saleTransaction.getProductId().getValue())
                .productName(saleTransaction.getProductName())
                .quantity(saleTransaction.getQuantity())
                .price(saleTransaction.getPrice())
                .createdAt(saleTransaction.getCreatedAt())
                .updatedAt(saleTransaction.getUpdatedAt())
                .build();
    }
}

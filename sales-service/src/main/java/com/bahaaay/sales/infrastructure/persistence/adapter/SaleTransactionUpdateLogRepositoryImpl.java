package com.bahaaay.sales.infrastructure.persistence.adapter;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;
import com.bahaaay.sales.domain.repository.SaleTransactionUpdateLogRepository;
import com.bahaaay.sales.infrastructure.persistence.mapper.sales.SalesLogsPersistenceMapper;
import com.bahaaay.sales.infrastructure.persistence.repository.sale.SaleTransactionUpdateLogJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleTransactionUpdateLogRepositoryImpl implements SaleTransactionUpdateLogRepository {
    private final SaleTransactionUpdateLogJpaRepository saleTransactionUpdateLogJpaRepository;
    private final SalesLogsPersistenceMapper salesLogsPersistenceMapper;

    public SaleTransactionUpdateLogRepositoryImpl(SaleTransactionUpdateLogJpaRepository saleTransactionUpdateLogJpaRepository, SalesLogsPersistenceMapper salesLogsPersistenceMapper) {
        this.saleTransactionUpdateLogJpaRepository = saleTransactionUpdateLogJpaRepository;
        this.salesLogsPersistenceMapper = salesLogsPersistenceMapper;
    }

    @Override
    public void save(SaleTransactionUpdateLog log) {
        saleTransactionUpdateLogJpaRepository
                .save(salesLogsPersistenceMapper
                        .saleTransactionUpdateLogToSaleTransactionUpdateLogJpaEntity(log));


    }

    @Override
    public List<SaleTransactionUpdateLog> findBySaleId(SaleId saleId) {
        return saleTransactionUpdateLogJpaRepository.findBySaleId(saleId.getValue())
                .stream()
                .map(salesLogsPersistenceMapper::saleTransactionUpdateLogJpaEntityToSaleTransactionUpdateLog)
                .toList();
    }
}

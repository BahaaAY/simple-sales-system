package com.bahaaay.sales.infrastructure.persistence.adapter;

import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.repository.SalesRepository;
import com.bahaaay.sales.infrastructure.persistence.mapper.sales.SalesPersistenceMapper;
import com.bahaaay.sales.infrastructure.persistence.repository.sale.SalesJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SalesRepositoryImpl implements SalesRepository {

    private final SalesJpaRepository salesJpaRepository;

    private final SalesPersistenceMapper salesPersistenceMapper;

    public SalesRepositoryImpl(SalesJpaRepository salesJpaRepository, SalesPersistenceMapper salesPersistenceMapper) {
        this.salesJpaRepository = salesJpaRepository;
        this.salesPersistenceMapper = salesPersistenceMapper;
    }

    @Override
    public Sale save(Sale sale) {
        return salesPersistenceMapper.saleJpaEntityToSale(
                salesJpaRepository.save(salesPersistenceMapper.saleToSaleJpaEntity(sale))
        );
    }


}

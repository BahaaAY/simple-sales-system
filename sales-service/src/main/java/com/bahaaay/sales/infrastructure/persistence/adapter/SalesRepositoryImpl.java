package com.bahaaay.sales.infrastructure.persistence.adapter;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.repository.SalesRepository;
import com.bahaaay.sales.infrastructure.persistence.mapper.sales.SalesPersistenceMapper;
import com.bahaaay.sales.infrastructure.persistence.repository.sale.SalesJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Sale> findById(SaleId id) {
        return salesJpaRepository.findById(id.getValue())
                .map(salesPersistenceMapper::saleJpaEntityToSale);
    }

    @Override
    public List<Sale> findAll(int offset, int limit) {
        int page = offset / limit;
        var pg = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        return salesJpaRepository.findAll(pg)
                .map(salesPersistenceMapper::saleJpaEntityToSale)
                .getContent();
    }

    @Override
    public long countAll() {
        return salesJpaRepository.count();
    }

    @Override
    public List<Sale> findByClientId(ClientId clientId, int offset, int limit) {
        int page = offset / limit;
        var pg = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        return salesJpaRepository.findByClientId(clientId.getValue(), pg)
                .map(salesPersistenceMapper::saleJpaEntityToSale)
                .getContent();
    }

    @Override
    public long countByClientId(ClientId clientId) {
        return salesJpaRepository.countByClientId(clientId.getValue());
    }


}

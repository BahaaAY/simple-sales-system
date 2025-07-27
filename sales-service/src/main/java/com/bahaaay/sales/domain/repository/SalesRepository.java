package com.bahaaay.sales.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.domain.entity.sales.Sale;

import java.util.Optional;
import java.util.List;

public interface SalesRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(SaleId id);
    /**
     * Fetch all sales
     */
    List<Sale> findAll(int offset, int limit);
    long countAll();

    /**
     * Fetch sales for one client
     */
    List<Sale> findByClientId(ClientId clientId, int offset, int limit);
    long countByClientId(ClientId clientId);

    /**
     * Check if a sale exists by its ID
     */
    boolean existsById(SaleId saleId);
}

package com.bahaaay.sales.domain.repository;

import com.bahaaay.sales.domain.entity.sales.Sale;

public interface SalesRepository {
    Sale save(Sale sale);
//    Optional<Sale> findById(UUID id);
}

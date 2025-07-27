package com.bahaaay.sales.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.domain.entity.sales.Sale;

import java.util.Optional;

public interface SalesRepository {
    Sale save(Sale sale);
    Optional<Sale> findById(SaleId id);
}

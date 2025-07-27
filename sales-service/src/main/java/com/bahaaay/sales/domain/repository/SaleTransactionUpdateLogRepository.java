package com.bahaaay.sales.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;

import java.util.List;

public interface SaleTransactionUpdateLogRepository {
    void save(SaleTransactionUpdateLog log);

    List<SaleTransactionUpdateLog> findBySaleId(SaleId saleId);
}

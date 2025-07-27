package com.bahaaay.sales.domain.repository;

import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;

public interface SaleTransactionUpdateLogRepository {
    void save(SaleTransactionUpdateLog log);
}

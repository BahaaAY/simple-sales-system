package com.bahaaay.sales.infrastructure.persistence.repository.sale;

import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;
import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleTransactionUpdateLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SaleTransactionUpdateLogJpaRepository extends JpaRepository<SaleTransactionUpdateLogJpaEntity, UUID> {

    List<SaleTransactionUpdateLogJpaEntity> findBySaleId(UUID saleId);
}

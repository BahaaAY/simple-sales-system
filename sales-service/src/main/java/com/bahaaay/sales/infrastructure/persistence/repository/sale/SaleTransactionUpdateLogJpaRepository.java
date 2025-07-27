package com.bahaaay.sales.infrastructure.persistence.repository.sale;

import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleTransactionUpdateLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleTransactionUpdateLogJpaRepository extends JpaRepository<SaleTransactionUpdateLogJpaEntity, UUID> {

}

package com.bahaaay.sales.infrastructure.persistence.repository.sale;

import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalesJpaRepository extends JpaRepository<SaleJpaEntity, UUID> {

}

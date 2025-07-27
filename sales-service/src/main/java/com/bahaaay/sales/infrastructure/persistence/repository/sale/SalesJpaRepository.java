package com.bahaaay.sales.infrastructure.persistence.repository.sale;

import com.bahaaay.sales.infrastructure.persistence.entity.sales.SaleJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SalesJpaRepository extends JpaRepository<SaleJpaEntity, UUID> {

    long countByClientId(UUID value);

    Page<SaleJpaEntity> findByClientId(UUID value, Pageable pg);

    
}

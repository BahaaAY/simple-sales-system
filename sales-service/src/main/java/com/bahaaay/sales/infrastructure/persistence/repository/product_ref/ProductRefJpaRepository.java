package com.bahaaay.sales.infrastructure.persistence.repository.product_ref;

import com.bahaaay.sales.infrastructure.persistence.entity.product_ref.ProductRefJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRefJpaRepository extends JpaRepository<ProductRefJpaEntity, UUID> {
}

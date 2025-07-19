package com.bahaaay.product.infrastructure.persistence.repository.product;

import com.bahaaay.product.infrastructure.persistence.entity.product.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> {
}

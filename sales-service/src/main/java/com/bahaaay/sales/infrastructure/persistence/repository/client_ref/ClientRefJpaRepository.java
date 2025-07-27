package com.bahaaay.sales.infrastructure.persistence.repository.client_ref;

import com.bahaaay.sales.infrastructure.persistence.entity.client_ref.ClientRefJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRefJpaRepository extends JpaRepository<ClientRefJpaEntity, UUID> {
}

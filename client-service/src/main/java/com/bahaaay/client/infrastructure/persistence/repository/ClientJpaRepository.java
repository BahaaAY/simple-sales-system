package com.bahaaay.client.infrastructure.persistence.repository;

import com.bahaaay.client.infrastructure.persistence.entity.client.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, UUID> {
}

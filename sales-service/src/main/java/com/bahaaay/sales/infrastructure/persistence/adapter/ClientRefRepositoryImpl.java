package com.bahaaay.sales.infrastructure.persistence.adapter;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.sales.domain.entity.client_ref.ClientRef;
import com.bahaaay.sales.domain.repository.ClientRefRepository;
import com.bahaaay.sales.infrastructure.persistence.mapper.client_ref.ClientRefPersistenceMapper;
import com.bahaaay.sales.infrastructure.persistence.repository.client_ref.ClientRefJpaRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRefRepositoryImpl implements ClientRefRepository {

    private final ClientRefJpaRepository clientRefJpaRepository;

    private final ClientRefPersistenceMapper clientRefPersistenceMapper;

    public ClientRefRepositoryImpl(ClientRefJpaRepository clientRefJpaRepository, ClientRefPersistenceMapper clientRefPersistenceMapper) {
        this.clientRefJpaRepository = clientRefJpaRepository;
        this.clientRefPersistenceMapper = clientRefPersistenceMapper;
    }

    @Override
    public Optional<ClientRef> findById(ClientId id) {
        return clientRefJpaRepository.findById(id.getValue())
                .map(clientRefPersistenceMapper::clientRefJpaEntityToClientRef);
    }
}

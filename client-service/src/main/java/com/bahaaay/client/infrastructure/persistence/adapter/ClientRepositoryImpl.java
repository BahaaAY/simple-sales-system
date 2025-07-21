package com.bahaaay.client.infrastructure.persistence.adapter;

import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.domain.repository.ClientRepository;
import com.bahaaay.client.infrastructure.persistence.mapper.client.ClientPersistenceMapper;
import com.bahaaay.client.infrastructure.persistence.repository.ClientJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    private final ClientPersistenceMapper clientPersistenceMapper;

    public ClientRepositoryImpl(ClientJpaRepository clientJpaRepository, ClientPersistenceMapper clientPersistenceMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientPersistenceMapper = clientPersistenceMapper;
    }

    @Override
    public Client save(Client client) {
        return clientPersistenceMapper.clientJpaEntityToClient(
                clientJpaRepository.save(
                        clientPersistenceMapper.clientToClientJpaEntity(
                                client
                        )
                )
        );
    }
}

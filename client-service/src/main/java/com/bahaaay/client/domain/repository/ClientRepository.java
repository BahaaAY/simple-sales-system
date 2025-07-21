package com.bahaaay.client.domain.repository;

import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);

    Optional<Client> findById(ClientId clientId);
}

package com.bahaaay.client.infrastructure.persistence.mapper.client;

import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.infrastructure.persistence.entity.client.ClientJpaEntity;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import org.springframework.stereotype.Component;

@Component
public class ClientPersistenceMapper {

    public ClientJpaEntity clientToClientJpaEntity(
            Client client
    ) {
        return ClientJpaEntity.builder()
                .id(client.getId().getValue())
                .name(client.getName())
                .lastName(client.getLastName())
                .mobileNumber(client.getMobileNumber())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }

    public Client clientJpaEntityToClient(
            ClientJpaEntity clientJpaEntity
    ) {
        return Client.load(
                ClientId.from(clientJpaEntity.getId()),
                clientJpaEntity.getName(),
                clientJpaEntity.getLastName(),
                clientJpaEntity.getMobileNumber(),
                clientJpaEntity.getCreatedAt(),
                clientJpaEntity.getUpdatedAt()
        );
    }
}

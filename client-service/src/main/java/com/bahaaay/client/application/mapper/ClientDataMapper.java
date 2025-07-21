package com.bahaaay.client.application.mapper;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.domain.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDataMapper {

    public Client createClientRequestToClient(
            CreateClientRequest createClientRequest
    ) {
        return Client.create(createClientRequest.name(), createClientRequest.lastName(), createClientRequest.mobileNumber());
    }

    public ClientDTO clientToClientDTO(Client savedClient) {
        return new ClientDTO(
                savedClient.getId().getValue(),
                savedClient.getName(),
                savedClient.getLastName(),
                savedClient.getMobileNumber(),
                savedClient.getCreatedAt(),
                savedClient.getUpdatedAt()
        );
    }
}

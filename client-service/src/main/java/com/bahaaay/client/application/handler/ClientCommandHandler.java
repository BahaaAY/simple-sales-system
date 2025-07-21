package com.bahaaay.client.application.handler;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.mapper.ClientDataMapper;
import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientCommandHandler {

    private final ClientRepository clientRepository;
    private final ClientDataMapper clientDataMapper;

    public ClientCommandHandler(ClientRepository clientRepository, ClientDataMapper clientDataMapper) {
        this.clientRepository = clientRepository;
        this.clientDataMapper = clientDataMapper;
    }

    public ClientDTO handleCreate(
            CreateClientRequest createClientRequest
    ) {

        Client client = clientDataMapper.createClientRequestToClient(createClientRequest);

        Client savedClient = clientRepository.save(client);

        // TODO publish client created event

        return clientDataMapper.clientToClientDTO(savedClient);

    }

}

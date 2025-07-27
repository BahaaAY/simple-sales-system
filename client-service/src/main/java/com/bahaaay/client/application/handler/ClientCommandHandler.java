package com.bahaaay.client.application.handler;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.dto.client.UpdateClientCommand;
import com.bahaaay.client.application.mapper.ClientDataMapper;
import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.domain.repository.ClientRepository;
import com.bahaaay.client.interfaces.messaging.publisher.ClientEventPublisher;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ClientCommandHandler {

    private final ClientRepository clientRepository;
    private final ClientDataMapper clientDataMapper;
    private final ClientEventPublisher clientEventPublisher;

    public ClientCommandHandler(ClientRepository clientRepository, ClientDataMapper clientDataMapper, ClientEventPublisher clientEventPublisher) {
        this.clientRepository = clientRepository;
        this.clientDataMapper = clientDataMapper;
        this.clientEventPublisher = clientEventPublisher;
    }

    @Transactional
    public ClientDTO handleCreate(
            CreateClientRequest createClientRequest
    ) {

        Client client = clientDataMapper.createClientRequestToClient(createClientRequest);

        Client savedClient = clientRepository.save(client);

        clientEventPublisher.publishCreated(client);

        return clientDataMapper.clientToClientDTO(savedClient);

    }

    @Transactional
    public ClientDTO handleUpdate(UpdateClientCommand updateClientCommand) {
        Client client = clientRepository.findById(updateClientCommand.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.updateName(updateClientCommand.name());

        client.updateLastName(updateClientCommand.lastName());

        client.updateMobileNumber(updateClientCommand.mobileNumber());



        Client updatedClient = clientRepository.save(client);

        clientEventPublisher.publishUpdated(updatedClient);

        return clientDataMapper.clientToClientDTO(updatedClient);
    }
}

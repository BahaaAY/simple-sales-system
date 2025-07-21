package com.bahaaay.client.application.handler;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.dto.client.UpdateClientCommand;
import com.bahaaay.client.application.mapper.ClientDataMapper;
import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.domain.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public ClientDTO handleUpdate(UpdateClientCommand updateClientCommand) {
        Client client = clientRepository.findById(updateClientCommand.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.updateName(updateClientCommand.name());

        client.updateLastName(updateClientCommand.lastName());

        client.updateMobileNumber(updateClientCommand.mobileNumber());



        Client updatedClient = clientRepository.save(client);

        // TODO publish client updated event

        return clientDataMapper.clientToClientDTO(updatedClient);
    }
}

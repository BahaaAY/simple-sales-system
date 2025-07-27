package com.bahaaay.client.application.handler;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.mapper.ClientDataMapper;
import com.bahaaay.client.domain.repository.ClientRepository;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class ClientQueryHandler {


    private final ClientRepository clientRepository;

    private final ClientDataMapper clientDataMapper;

    public ClientQueryHandler(ClientRepository clientRepository, ClientDataMapper clientDataMapper) {
        this.clientRepository = clientRepository;
        this.clientDataMapper = clientDataMapper;
    }

    /**
     * Handles the retrieval of a client by its ID.
     *
     * @param clientId the UUID of the client to retrieve
     * @return ClientDTO containing client details
     */
    @Transactional(readOnly = true)
    public ClientDTO handleGetById(UUID clientId) {
        return clientRepository.findById(ClientId.from(clientId))
                .map(clientDataMapper::clientToClientDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
    }
}

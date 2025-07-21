package com.bahaaay.client.application;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.dto.client.UpdateClientCommand;
import com.bahaaay.client.application.dto.client.UpdateClientRequest;
import com.bahaaay.client.application.handler.ClientCommandHandler;
import com.bahaaay.client.application.handler.ClientQueryHandler;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientApplicationService {

    private final ClientCommandHandler clientCommandHandler;
    private final ClientQueryHandler clientQueryHandler;

    public ClientApplicationService(ClientCommandHandler clientCommandHandler, ClientQueryHandler clientQueryHandler) {
        this.clientCommandHandler = clientCommandHandler;
        this.clientQueryHandler = clientQueryHandler;
    }

    public ClientDTO createClient( CreateClientRequest createClientRequest) {
        return clientCommandHandler.handleCreate(createClientRequest);
    }

    public ClientDTO getClientById(UUID clientId) {
        return clientQueryHandler.handleGetById(clientId);
    }

    public ClientDTO updateClient(UUID clientId, UpdateClientRequest updateClientRequest) {
        return clientCommandHandler.handleUpdate(new UpdateClientCommand(
                ClientId.from(clientId),
                updateClientRequest.name(),
                updateClientRequest.lastName(),
                updateClientRequest.mobileNumber()
        ));
    }
}

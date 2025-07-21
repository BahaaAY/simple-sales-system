package com.bahaaay.client.application;

import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.handler.ClientCommandHandler;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ClientApplicationService {

    private final ClientCommandHandler clientCommandHandler;

    public ClientApplicationService(ClientCommandHandler clientCommandHandler) {
        this.clientCommandHandler = clientCommandHandler;
    }

    public ClientDTO createClient( CreateClientRequest createClientRequest) {
        return clientCommandHandler.handleCreate(createClientRequest);
    }
}

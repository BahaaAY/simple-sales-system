package com.bahaaay.client.interfaces.rest;

import com.bahaaay.client.application.ClientApplicationService;
import com.bahaaay.client.application.dto.client.ClientDTO;
import com.bahaaay.client.application.dto.client.CreateClientRequest;
import com.bahaaay.client.application.dto.client.UpdateClientRequest;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientApplicationService clientApplicationService;

    public ClientController(ClientApplicationService clientApplicationService) {
        this.clientApplicationService = clientApplicationService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid CreateClientRequest createClientRequest) {
        ClientDTO clientDTO = clientApplicationService.createClient(createClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("clientId") UUID clientId) {
        ClientDTO clientDTO = clientApplicationService.getClientById(clientId);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable("clientId") UUID clientId,
            @RequestBody @Valid UpdateClientRequest updateClientRequest
    ) {
        ClientDTO clientDTO = clientApplicationService.updateClient(clientId, updateClientRequest);
        return ResponseEntity.ok(clientDTO);
    }
}

package com.bahaaay.client.application.dto.client;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;

public record UpdateClientCommand(
        ClientId clientId,
        String name,
        String lastName,
        String mobileNumber
) {
}

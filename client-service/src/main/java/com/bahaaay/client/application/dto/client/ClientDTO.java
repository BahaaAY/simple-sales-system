package com.bahaaay.client.application.dto.client;

import java.time.Instant;
import java.util.UUID;

public record ClientDTO(
        UUID id,
        String name,
        String lastName,
        String mobileNumber,
        Instant createdAt,
        Instant updatedAt
) {
}

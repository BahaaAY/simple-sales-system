package com.bahaaay.client.application.dto.client;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientRequest(
        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank String mobileNumber
) {
}

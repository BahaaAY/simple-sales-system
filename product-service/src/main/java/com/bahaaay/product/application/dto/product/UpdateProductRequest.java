package com.bahaaay.product.application.dto.product;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String category
) {
}

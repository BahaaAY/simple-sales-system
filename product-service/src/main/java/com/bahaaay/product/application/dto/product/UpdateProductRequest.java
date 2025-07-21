package com.bahaaay.product.application.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String category,
        @NotNull(message = "Price cannot be null") BigDecimal price
) {
}

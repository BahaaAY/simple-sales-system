package com.bahaaay.product.application.dto.product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Category cannot be null")
        String category,
        @NotNull(message = "Price cannot be null")
        BigDecimal price
) {
}

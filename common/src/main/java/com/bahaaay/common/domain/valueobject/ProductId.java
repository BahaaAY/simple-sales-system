package com.bahaaay.common.domain.valueobject;

import java.util.UUID;
// Represents a unique identifier for a product in the system.
public class ProductId {
    private final UUID value;

    public ProductId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        this.value = value;
    }

    public static ProductId from(UUID value) {
        return new ProductId(value);
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

}

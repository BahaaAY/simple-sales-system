package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;
// Represents a unique identifier for a product in the system.
public final class ProductId extends BaseIdentifier {

    private ProductId(UUID value) {
        super(value);
    }

    public static ProductId from(UUID value) {
        return new ProductId(value);
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }

}

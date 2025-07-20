package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public sealed abstract class BaseIdentifier implements Identifier permits ProductId, ClientId {
    private final UUID value;

    protected BaseIdentifier(UUID value) {
        if (value == null) throw new IllegalArgumentException("ID cannot be null");
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}

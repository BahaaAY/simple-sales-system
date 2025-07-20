package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public final class ClientId extends BaseIdentifier {
    private ClientId(UUID value) {
        super(value);
    }

    public static ClientId from(UUID value) {
        return new ClientId(value);
    }

    public static ClientId generate() {
        return new ClientId(UUID.randomUUID());
    }

}

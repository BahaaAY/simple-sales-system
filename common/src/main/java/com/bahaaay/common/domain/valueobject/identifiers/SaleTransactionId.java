package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public final class SaleTransactionId extends BaseIdentifier{
    private SaleTransactionId(UUID value) {
        super(value);
    }

    public static SaleTransactionId from(UUID value) {
        return new SaleTransactionId(value);
    }

    public static SaleTransactionId generate() {
        return new SaleTransactionId(UUID.randomUUID());
    }
}

package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public final class SaleId extends BaseIdentifier{
    private SaleId(UUID value) {
        super(value);
    }

    public static SaleId from(UUID value) {
        return new SaleId(value);
    }
    public static SaleId generate() {
        return new SaleId(UUID.randomUUID());
    }

}

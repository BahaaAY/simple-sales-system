package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public final class SaleTransactionQuantityUpdateLogId extends BaseIdentifier {

    private SaleTransactionQuantityUpdateLogId(UUID value) {
        super(value);
    }

    public static SaleTransactionQuantityUpdateLogId from(UUID value) {
        return new SaleTransactionQuantityUpdateLogId(value);
    }

    public static SaleTransactionQuantityUpdateLogId generate() {
        return new SaleTransactionQuantityUpdateLogId(UUID.randomUUID());
    }
}

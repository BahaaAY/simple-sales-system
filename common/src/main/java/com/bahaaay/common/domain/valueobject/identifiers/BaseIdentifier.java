package com.bahaaay.common.domain.valueobject.identifiers;

import com.bahaaay.common.exception.BadRequestException;

import java.util.Objects;
import java.util.UUID;

public sealed abstract class BaseIdentifier implements Identifier permits ClientId, ProductId, SaleId, SaleTransactionId, SaleTransactionQuantityUpdateLogId {
    private final UUID value;

    protected BaseIdentifier(UUID value) {
        if (value == null) throw new BadRequestException("ID cannot be null");
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseIdentifier)) return false;
        BaseIdentifier that = (BaseIdentifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

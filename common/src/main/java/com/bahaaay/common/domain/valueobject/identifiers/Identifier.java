package com.bahaaay.common.domain.valueobject.identifiers;

import java.util.UUID;

public sealed interface Identifier permits
        BaseIdentifier
        {
    UUID getValue();

}

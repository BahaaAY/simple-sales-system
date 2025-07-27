package com.bahaaay.sales.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.sales.domain.entity.client_ref.ClientRef;

import java.util.Optional;

public interface ClientRefRepository {

    Optional<ClientRef> findById(ClientId id);
}

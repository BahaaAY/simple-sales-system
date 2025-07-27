package com.bahaaay.sales.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.sales.domain.entity.product_ref.ProductRef;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRefRepository {


    Optional<ProductRef> findById(ProductId id);

    List<ProductRef> findAllById(Iterable<ProductId> ids);
}

package com.bahaaay.product.domain.repository;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.product.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(ProductId id);

}

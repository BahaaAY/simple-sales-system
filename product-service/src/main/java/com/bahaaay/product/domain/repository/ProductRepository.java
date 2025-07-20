package com.bahaaay.product.domain.repository;

import com.bahaaay.common.domain.valueobject.ProductId;
import com.bahaaay.product.domain.entity.Product;

import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Product findById(ProductId id);

}

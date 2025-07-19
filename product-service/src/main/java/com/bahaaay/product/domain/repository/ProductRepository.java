package com.bahaaay.product.domain.repository;

import com.bahaaay.product.domain.entity.Product;

import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
}

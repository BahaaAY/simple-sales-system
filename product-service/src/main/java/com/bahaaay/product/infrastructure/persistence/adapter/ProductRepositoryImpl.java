package com.bahaaay.product.infrastructure.persistence.adapter;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.domain.repository.ProductRepository;
import com.bahaaay.product.infrastructure.persistence.mapper.product.ProductPersistenceMapper;
import com.bahaaay.product.infrastructure.persistence.repository.product.ProductJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final ProductPersistenceMapper productPersistenceMapper;
    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository, ProductPersistenceMapper productPersistenceMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productPersistenceMapper = productPersistenceMapper;
    }

    @Override
    public Product save(Product product) {
        return productPersistenceMapper.productJpaEntityToProduct(productJpaRepository.save(productPersistenceMapper.productToProductJpaEntity(product)));
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return productJpaRepository.findById(id.getValue())
                .map(productPersistenceMapper::productJpaEntityToProduct);
    }
}

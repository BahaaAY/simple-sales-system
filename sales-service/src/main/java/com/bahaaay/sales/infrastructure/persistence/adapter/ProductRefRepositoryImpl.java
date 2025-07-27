package com.bahaaay.sales.infrastructure.persistence.adapter;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.sales.domain.entity.product_ref.ProductRef;
import com.bahaaay.sales.domain.repository.ProductRefRepository;
import com.bahaaay.sales.infrastructure.persistence.entity.product_ref.ProductRefJpaEntity;
import com.bahaaay.sales.infrastructure.persistence.mapper.product_ref.ProductRefPersistenceMapper;
import com.bahaaay.sales.infrastructure.persistence.repository.product_ref.ProductRefJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

@Repository
public class ProductRefRepositoryImpl implements ProductRefRepository {
    private final ProductRefJpaRepository productRefJpaRepository;
    private final ProductRefPersistenceMapper productRefPersistenceMapper;

    public ProductRefRepositoryImpl(ProductRefJpaRepository productRefJpaRepository, ProductRefPersistenceMapper productRefPersistenceMapper) {
        this.productRefJpaRepository = productRefJpaRepository;
        this.productRefPersistenceMapper = productRefPersistenceMapper;
    }

    @Override
    public Optional<ProductRef> findById(ProductId id) {
        return productRefJpaRepository.findById(id.getValue()).map(
                productRefPersistenceMapper::productRefJpaEntityToProductRef
        );
    }

    @Override
    public List<ProductRef> findAllById(Iterable<ProductId> ids) {
        // fetch all JPA entities in one query
        Iterable<ProductRefJpaEntity> entities =
                productRefJpaRepository.findAllById(StreamSupport.stream(ids.spliterator(), false)
                        .map(ProductId::getValue)
                        .collect(Collectors.toList()));

        // map them into domain objects
        return StreamSupport.stream(entities.spliterator(), false)
                .map(productRefPersistenceMapper::productRefJpaEntityToProductRef)
                .collect(Collectors.toList());
    }


}

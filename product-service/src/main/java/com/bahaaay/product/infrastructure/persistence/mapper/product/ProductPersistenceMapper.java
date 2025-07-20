package com.bahaaay.product.infrastructure.persistence.mapper.product;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.infrastructure.persistence.entity.product.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductJpaEntity productToProductJpaEntity(
            Product product
    ) {
        return  ProductJpaEntity.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public Product productJpaEntityToProduct(
            ProductJpaEntity productJpaEntity
    ) {
        return Product.load(
                ProductId.from(productJpaEntity.getId()),
                productJpaEntity.getName(),
                productJpaEntity.getDescription(),
                productJpaEntity.getCategory(),
                productJpaEntity.getCreatedAt(),
                productJpaEntity.getUpdatedAt()
        );
    }
}

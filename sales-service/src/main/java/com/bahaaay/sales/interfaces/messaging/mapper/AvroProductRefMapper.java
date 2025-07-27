package com.bahaaay.sales.interfaces.messaging.mapper;

import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import com.bahaaay.sales.infrastructure.persistence.entity.product_ref.ProductRefJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AvroProductRefMapper {

    public ProductRefJpaEntity fromCreatedEvent(ProductCreatedEvent event) {
        return ProductRefJpaEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .price(event.getPrice())
                .build();
    }

    public ProductRefJpaEntity fromUpdatedEvent(ProductUpdatedEvent event) {
        return ProductRefJpaEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .price(event.getPrice())
                .build();
    }

}

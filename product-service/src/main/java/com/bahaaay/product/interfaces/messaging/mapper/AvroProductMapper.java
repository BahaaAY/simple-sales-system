package com.bahaaay.product.interfaces.messaging.mapper;

import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import com.bahaaay.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.apache.avro.Conversions;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvroProductMapper {

    public ProductCreatedEvent productToProductCreatedEvent(Product p) {
        return ProductCreatedEvent.newBuilder()
                .setId(p.getId().getValue())
                .setName(p.getName())
                .setDescription(p.getDescription())
                .setCategory(p.getCategory())
                .setPrice(p.getPrice())
                .setCreatedAt(p.getCreatedAt())
                .setUpdatedAt(p.getUpdatedAt())
                .build();
    }

    public ProductUpdatedEvent productToProductUpdatedEvent(Product p) {
        return ProductUpdatedEvent.newBuilder()
                .setId(p.getId().getValue())
                .setName(p.getName())
                .setDescription(p.getDescription())
                .setCategory(p.getCategory())
                .setPrice(p.getPrice())
                .setCreatedAt(p.getCreatedAt())
                .setUpdatedAt(p.getUpdatedAt())
                .build();
    }
}
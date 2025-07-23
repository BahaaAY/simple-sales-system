package com.bahaaay.client.interfaces.messaging.mapper;

import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.common.avro.client.ClientCreatedEvent;
import com.bahaaay.common.avro.client.ClientUpdatedEvent;
import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvroClientMapper {

    public ClientCreatedEvent clientToClientCreatedEvent(Client c) {
        return ClientCreatedEvent.newBuilder()
                .setId(c.getId().getValue())
                .setName(c.getName())
                .setLastName(c.getLastName())
                .setMobileNumber(c.getMobileNumber())
                .setCreatedAt(c.getCreatedAt())
                .setUpdatedAt(c.getUpdatedAt())
                .build();
    }

    public ClientUpdatedEvent clientToClientUpdatedEvent(Client c) {
        return ClientUpdatedEvent.newBuilder()
                .setId(c.getId().getValue())
                .setName(c.getName())
                .setLastName(c.getLastName())
                .setMobileNumber(c.getMobileNumber())
                .setCreatedAt(c.getCreatedAt())
                .setUpdatedAt(c.getUpdatedAt())
                .build();
    }


}

package com.bahaaay.sales.interfaces.messaging.mapper;

import com.bahaaay.common.avro.client.ClientCreatedEvent;
import com.bahaaay.common.avro.client.ClientUpdatedEvent;
import com.bahaaay.sales.infrastructure.persistence.entity.client_ref.ClientRefJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AvroClientRefMapper {

    public ClientRefJpaEntity fromCreatedEvent(ClientCreatedEvent event) {
        return ClientRefJpaEntity.builder()
                .id(event.getId())
                .fullName(event.getName() + " " + event.getLastName())
                .mobileNumber(event.getMobileNumber())
                .build();
    }

    public ClientRefJpaEntity fromUpdatedEvent(ClientUpdatedEvent event) {
        return ClientRefJpaEntity.builder()
                .id(event.getId())
                .fullName(event.getName() + " " + event.getLastName())
                .mobileNumber(event.getMobileNumber())
                .build();
    }
}

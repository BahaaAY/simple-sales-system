package com.bahaaay.client.interfaces.messaging.publisher;

import com.bahaaay.client.domain.entity.Client;
import com.bahaaay.client.interfaces.messaging.mapper.AvroClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientEventPublisher {

    private final KafkaTemplate<UUID,Object> template;
    private final AvroClientMapper mapper;

    private static final String TOPIC = "client-events.v1";

    public void publishCreated(Client c) {
        template.send(TOPIC, c.getId().getValue(), mapper.clientToClientCreatedEvent(c));
    }

    public void publishUpdated(Client c) {
        template.send(TOPIC, c.getId().getValue(), mapper.clientToClientUpdatedEvent(c));
    }
}

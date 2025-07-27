package com.bahaaay.sales.interfaces.messaging.consumer;

import com.bahaaay.common.avro.client.ClientCreatedEvent;
import com.bahaaay.common.avro.client.ClientUpdatedEvent;
import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import com.bahaaay.sales.infrastructure.persistence.repository.client_ref.ClientRefJpaRepository;
import com.bahaaay.sales.infrastructure.persistence.repository.product_ref.ProductRefJpaRepository;
import com.bahaaay.sales.interfaces.messaging.mapper.AvroClientRefMapper;
import com.bahaaay.sales.interfaces.messaging.mapper.AvroProductRefMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReferenceSnapshotConsumers {

    private final ProductRefJpaRepository productRefJpaRepository;
    private final ClientRefJpaRepository clientRefJpaRepository;

    private final AvroProductRefMapper avroProductRefMapper;
    private final AvroClientRefMapper avroClientRefMapper;

    public ReferenceSnapshotConsumers(ProductRefJpaRepository productRefJpaRepository, ClientRefJpaRepository clientRefJpaRepository, AvroProductRefMapper avroProductRefMapper, AvroClientRefMapper avroClientRefMapper) {
        this.productRefJpaRepository = productRefJpaRepository;
        this.clientRefJpaRepository = clientRefJpaRepository;
        this.avroProductRefMapper = avroProductRefMapper;
        this.avroClientRefMapper = avroClientRefMapper;
    }

    @KafkaListener(
            topics = "${spring.kafka.topics.products}",
            groupId = "${spring.kafka.groups.products}"
    )
    public void onProductEvent(SpecificRecord record, Acknowledgment ack) {
        log.info("Received record of type: {}", record.getClass().getName());
        if (record instanceof ProductCreatedEvent e) {
            handleProductCreated(e);
        } else if (record instanceof ProductUpdatedEvent e) {
            handleProductUpdated(e);
        } else {
            log.warn("Received unsupported record type: {}", record.getClass().getName());
        }
        ack.acknowledge(); // acknowledge the message after processing
    }

    private void handleProductCreated(ProductCreatedEvent e) {
        productRefJpaRepository.save(avroProductRefMapper.fromCreatedEvent(e));
    }

    // same method but keeping it for clarity, as it might be used in other places or for future extensions
    private void handleProductUpdated(ProductUpdatedEvent e) {
        productRefJpaRepository.save(avroProductRefMapper.fromUpdatedEvent(e));
    }

    @KafkaListener(
            topics = "${spring.kafka.topics.clients}",
            groupId = "${spring.kafka.groups.clients}"
    )
    public void onClientEvent(SpecificRecord specificRecord, Acknowledgment ack) {
        log.info("Received record of type: {}", specificRecord.getClass().getName());
        if (specificRecord instanceof ClientCreatedEvent e) {
            handleClientCreated(e);
        } else if (specificRecord instanceof ClientUpdatedEvent e) {
            handleClientUpdated(e);
        } else {
            log.warn("Received unsupported record type: {}", specificRecord.getClass().getName());
        }
        ack.acknowledge();
    }

    private void handleClientCreated(ClientCreatedEvent e) {
        clientRefJpaRepository.save(avroClientRefMapper.fromCreatedEvent(e));
    }

    private void handleClientUpdated(ClientUpdatedEvent e) {
        clientRefJpaRepository.save(avroClientRefMapper.fromUpdatedEvent(e));
    }


}

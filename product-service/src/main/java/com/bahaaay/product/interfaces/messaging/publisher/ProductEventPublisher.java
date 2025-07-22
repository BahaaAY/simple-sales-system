package com.bahaaay.product.interfaces.messaging.publisher;

import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.interfaces.messaging.mapper.AvroProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final KafkaTemplate<String,Object> template;
    private final AvroProductMapper mapper;

    private static final String TOPIC = "product-events.v1";

    public void publishCreated(Product p) {
        template.send(TOPIC, p.getId().toString(), mapper.productToProductCreatedEvent(p));
    }

    public void publishUpdated(Product p) {
        template.send(TOPIC, p.getId().toString(), mapper.productToProductUpdatedEvent(p));
    }

}

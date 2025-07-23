package com.bahaaay.client.interfaces.messaging.config;


import com.bahaaay.common.avro.product.ProductCreatedEvent;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<UUID, Object> avroProducerFactory(KafkaProperties props) {
        Map<String,Object> cfg = props.buildProducerProperties();
        cfg.put(
                AbstractKafkaSchemaSerDeConfig.VALUE_SUBJECT_NAME_STRATEGY,
                TopicRecordNameStrategy.class.getName()
        );
        return new DefaultKafkaProducerFactory<>(cfg);
    }

    @Bean
    public KafkaTemplate<UUID,Object> avroKafkaTemplate(
            ProducerFactory<UUID,Object> factory) {
        return new KafkaTemplate<>(factory);
    }
}


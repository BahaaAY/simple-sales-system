package com.bahaaay.sales.interfaces.messaging.config;

import com.bahaaay.common.avro.client.ClientCreatedEvent;
import com.bahaaay.common.avro.client.ClientUpdatedEvent;
import com.bahaaay.common.avro.product.ProductCreatedEvent;
import com.bahaaay.common.avro.product.ProductUpdatedEvent;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<UUID, SpecificRecord> consumerFactory(
            KafkaProperties properties) {
        Map<String, Object>  props = properties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<UUID, SpecificRecord>
    kafkaListenerContainerFactory(ConsumerFactory<UUID, SpecificRecord> cf,
                                  KafkaProperties props) {
        var factory = new ConcurrentKafkaListenerContainerFactory<UUID, SpecificRecord>();
        factory.setConsumerFactory(cf);

        // manual acks
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // concurrency
        factory.setConcurrency(props.getListener().getConcurrency());

        return factory;
    }


}

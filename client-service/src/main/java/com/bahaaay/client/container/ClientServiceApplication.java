package com.bahaaay.client.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bahaaay.client", "com.bahaaay.common.exception"})
@EntityScan(basePackages = "com.bahaaay.client.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.bahaaay.client.infrastructure.persistence.repository")
public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}

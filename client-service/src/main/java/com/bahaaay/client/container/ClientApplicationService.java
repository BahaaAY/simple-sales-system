package com.bahaaay.client.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.bahaaay.client")
@EntityScan(basePackages = "com.bahaaay.product.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.bahaaay.client.infrastructure.persistence.repository")
public class ClientApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplicationService.class, args);
    }
}

package com.bahaaay.product.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.bahaaay.product")
@EntityScan(basePackages = "com.bahaaay.product.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.bahaaay.product.infrastructure.persistence.repository")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}

package com.bahaaay.sales.container;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.bahaaay.sales",
        "com.bahaaay.common.exception"
        })
@EntityScan(basePackages = "com.bahaaay.sales.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.bahaaay.sales.infrastructure.persistence.repository")
public class SalesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesServiceApplication.class, args);
    }
}

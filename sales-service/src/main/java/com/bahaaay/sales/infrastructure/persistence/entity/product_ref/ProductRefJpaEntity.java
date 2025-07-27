package com.bahaaay.sales.infrastructure.persistence.entity.product_ref;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_ref")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRefJpaEntity {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}

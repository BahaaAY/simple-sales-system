package com.bahaaay.sales.infrastructure.persistence.entity.client_ref;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "client_ref")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRefJpaEntity {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String fullName;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;
}

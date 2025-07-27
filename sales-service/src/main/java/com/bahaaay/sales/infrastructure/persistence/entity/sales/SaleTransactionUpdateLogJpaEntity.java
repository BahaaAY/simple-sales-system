package com.bahaaay.sales.infrastructure.persistence.entity.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sale_transaction_update_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransactionUpdateLogJpaEntity {
    @Id
    private UUID id;
    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;
    @Column(name = "sale_id", nullable = false)
    private UUID saleId;
    @Column(name = "old_quantity", nullable = false)
    private int oldQuantity;
    @Column(name = "new_quantity", nullable = false)
    private int newQuantity;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}

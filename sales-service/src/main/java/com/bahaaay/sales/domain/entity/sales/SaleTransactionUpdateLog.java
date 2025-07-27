package com.bahaaay.sales.domain.entity.sales;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionQuantityUpdateLogId;

import java.time.Instant;

/**
 * Represents a log entry for updates made to the quantity of a sale transaction.
 * This entity captures the old and new quantities, along with the timestamp of the update.
 * it is treated as a separate aggregate root in the domain model, not directly linked to the Sale aggregate.
 */
public class SaleTransactionUpdateLog {
    private SaleTransactionQuantityUpdateLogId id;
    private SaleId saleId;
    private SaleTransactionId transactionId;
    private int oldQuantity;
    private int newQuantity;
    private Instant updatedAt;

    public SaleTransactionUpdateLog(SaleTransactionQuantityUpdateLogId id, SaleId saleId, SaleTransactionId transactionId, int oldQuantity, int newQuantity, Instant updatedAt) {
        this.id = id;
        this.saleId = saleId;
        this.transactionId = transactionId;
        this.oldQuantity = oldQuantity;
        this.newQuantity = newQuantity;
        this.updatedAt = updatedAt;
    }

    public static SaleTransactionUpdateLog create(SaleId saleId, SaleTransactionId transactionId, int oldQuantity, int newQuantity) {
        return new SaleTransactionUpdateLog(
                SaleTransactionQuantityUpdateLogId.generate(),
                saleId,
                transactionId,
                oldQuantity,
                newQuantity,
                Instant.now()
        );
    }

    public static SaleTransactionUpdateLog load(SaleTransactionQuantityUpdateLogId id, SaleId saleId, SaleTransactionId transactionId, int oldQuantity, int newQuantity, Instant updatedAt) {
        return new SaleTransactionUpdateLog(id, saleId, transactionId, oldQuantity, newQuantity, updatedAt);
    }

    public SaleTransactionQuantityUpdateLogId getId() {
        return id;
    }

    public SaleId getSaleId() {
        return saleId;
    }

    public SaleTransactionId getTransactionId() {
        return transactionId;
    }

    public int getOldQuantity() {
        return oldQuantity;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}

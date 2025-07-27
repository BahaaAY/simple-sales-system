package com.bahaaay.sales.domain.entity.sales;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;

import java.math.BigDecimal;
import java.time.Instant;

public class SaleTransaction {
    private SaleTransactionId id;
    private SaleId saleId;
    private ProductId productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private Instant createdAt;
    private Instant updatedAt;

    public SaleTransaction(SaleTransactionId id, SaleId saleId, ProductId productId, String productName, int quantity, BigDecimal price, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate();
    }

    public static SaleTransaction create(SaleId saleId, ProductId productId, String productName, int quantity, BigDecimal price) {
        return new SaleTransaction(SaleTransactionId.generate(), saleId, productId, productName, quantity, price, Instant.now(), Instant.now());
    }

    public static SaleTransaction load(SaleTransactionId id, SaleId saleId, ProductId productId, String productName, int quantity, BigDecimal price, Instant createdAt, Instant updatedAt) {
        return new SaleTransaction(id, saleId, productId, productName, quantity, price, createdAt, updatedAt);
    }

    private void validate() {
        if (saleId == null) throw new IllegalArgumentException("saleId required");
        if (productId == null) throw new IllegalArgumentException("productId required");
        if (productName == null
                || productName.isBlank()) throw new IllegalArgumentException("productName required");
        if (quantity < 0) throw new IllegalArgumentException("quantity ≥ 0");
        if (price == null
                || price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("price ≥ 0");
    }

    public SaleTransactionId getId() {
        return id;
    }

    public SaleId getSaleId() {
        return saleId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}

package com.bahaaay.sales.domain.entity.sales;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;
import com.bahaaay.sales.domain.dto.TransactionQuantityUpdate;
import com.bahaaay.sales.domain.entity.product_ref.ProductRef;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sale Aggregate Root.
 */
public class Sale {

    private SaleId id;
    private ClientId clientId;
    private String seller;
    private List<SaleTransaction> transactions = new ArrayList<>();

    private Instant createdAt;
    private Instant updatedAt;

    private Sale(SaleId id, ClientId clientId, String seller,List<SaleTransaction> transactions, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.clientId = clientId;
        this.seller = seller;
        this.transactions = new ArrayList<>();
        this.transactions.addAll(transactions);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate();
    }

    /** Create a brand-new sale. */
    public static Sale create(ClientId clientId, String seller) {
        return new Sale(SaleId.generate(), clientId, seller, List.of(),  Instant.now(), Instant.now());
    }

    /** Rehydrate an existing sale */
    public static Sale load(SaleId id, ClientId clientId, String seller, List<SaleTransaction> transactions, Instant createdAt, Instant updatedAt) {
        return new Sale(id, clientId, seller, transactions, createdAt, updatedAt);
    }

    public void addTransaction(ProductRef product, int quantity) {
        this.transactions.add(
                SaleTransaction.create(
                        this.id,
                        product.getId(),
                        product.getName(),
                        quantity,
                        product.getPrice()
                )
        );
    }

    private void validate() {
        if (clientId == null) throw new IllegalArgumentException("clientId required");
        if (seller == null || seller.isBlank()) throw new IllegalArgumentException("seller required");
    }


    public BigDecimal total() {
        return transactions.stream()
                .map(t -> t.getPrice().multiply(BigDecimal.valueOf(t.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public TransactionQuantityUpdate updateTransactionQuantity(SaleTransactionId transactionId, int newQuantity) {
        SaleTransaction transaction = transactions.stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        int currentQuantity = transaction.getQuantity();
        transaction.updateQuantity(newQuantity);
        this.updatedAt = Instant.now();

        return new TransactionQuantityUpdate(transactionId, currentQuantity, newQuantity);
    }

    public SaleId getId() {
        return id;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public String getSeller() {
        return seller;
    }

    public List<SaleTransaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }


}

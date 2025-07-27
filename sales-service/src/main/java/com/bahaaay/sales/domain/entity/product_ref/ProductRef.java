package com.bahaaay.sales.domain.entity.product_ref;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;

import java.math.BigDecimal;

// Represents a reference to a product (from product-service) in the sales domain.
public class ProductRef {
    private ProductId id;
    private String name;
    private BigDecimal price;

    private ProductRef(ProductId id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductRef load(ProductId productId, String name, BigDecimal price) {
        return new ProductRef(productId, name, price);
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

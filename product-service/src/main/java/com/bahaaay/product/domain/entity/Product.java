package com.bahaaay.product.domain.entity;

import java.time.Instant;
import java.util.UUID;

/** Product Aggregate Root Entity.
 * This class is immutable and provides factory methods for creating new instances or loading existing ones.
 */

public class Product {
    private UUID id;
    private String name;
    private String description;
    private String category;

    private Instant createdAt;
    private Instant updatedAt;

    private Product(UUID id, String name, String description, String category, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** * Factory method to create a new Product instance.
     *
     * @param id          Unique identifier for the product.
     * @param name        Name of the product.
     * @param description Description of the product.
     * @param category    Category of the product.
     * @return A new Product instance with the current timestamp for createdAt and updatedAt.
     */
    public static Product create(UUID id, String name, String description, String category) {
        Instant now = Instant.now();
        return new Product(id, name, description, category, now, now);
    }

    /** Factory method to load an existing Product instance.
     * used to hydrate a product from a database or other storage.
     * @param id          Unique identifier for the product.
     * @param name        Name of the product.
     * @param description Description of the product.
     * @param category    Category of the product.
     * @param createdAt   Creation timestamp of the product.
     * @param updatedAt   Last updated timestamp of the product.
     * @return A new Product instance with the provided parameters.
     */
    public static Product load(UUID id, String name, String description, String category, Instant createdAt, Instant updatedAt) {
        return new Product(id, name, description, category, createdAt, updatedAt);
    }


    /** Getters */

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

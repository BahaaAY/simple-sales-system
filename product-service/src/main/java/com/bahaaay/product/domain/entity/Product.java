package com.bahaaay.product.domain.entity;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.common.exception.BadRequestException;

import java.math.BigDecimal;
import java.time.Instant;

/** Product Aggregate Root Entity.
 * This class is immutable and provides factory methods for creating new instances or loading existing ones.
 */

public class Product {
    private ProductId id;
    private String name;
    private String description;
    private String category;

    private BigDecimal price;

    private Instant createdAt;
    private Instant updatedAt;

    private Product(ProductId id, String name, String description, String category, BigDecimal price, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate(); // Validate the product fields upon creation or loading
    }

    /** * Factory method to create a new Product instance.
     *
     * @param name        Name of the product.
     * @param description Description of the product.
     * @param category    Category of the product.
     * @return A new Product instance with the current timestamp for createdAt and updatedAt.
     */
    public static Product create(String name, String description, String category, BigDecimal price) {
        Instant now = Instant.now();
        return new Product(ProductId.generate(), name, description, category, price, now, now);
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
    public static Product load(ProductId id, String name, String description, String category, BigDecimal price, Instant createdAt, Instant updatedAt) {
        return new Product(id, name, description, category, price, createdAt, updatedAt);
    }

    /** Validation */

    public void validate() {
        validateId();
        validateName();
        validateDescription();
        validateCategory();
        validatePrice();
    }

    private void validateId() {
        if (id == null) {
            throw new BadRequestException("Product ID cannot be null");
        }
    }

    private void validateName() {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Product name cannot be null or empty");
        }
    }

    private void validateDescription() {
        if (description == null || description.isBlank()) {
            throw new BadRequestException("Product description cannot be null or empty");
        }
    }

    private void validateCategory() {
        if (category == null || category.isBlank()) {
            throw new BadRequestException("Product category cannot be null or empty");
        }
    }

    private void validatePrice() {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Product price cannot be null or negative");
        }
    }

    public void updateName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
            this.updatedAt = Instant.now();
            validateName();
        }
    }

    public void updateDescription(String description) {
        if (description != null && !description.isBlank()) {
            this.description = description;
            this.updatedAt = Instant.now();
            validateDescription();
        }
    }

    public void updateCategory(String category) {
        if (category != null && !category.isBlank()) {
            this.category = category;
            this.updatedAt = Instant.now();
            validateCategory();
        }
    }

    public void updatePrice(BigDecimal price) {
        if (price != null && price.compareTo(BigDecimal.ZERO) >= 0) {
            this.price = price;
            this.updatedAt = Instant.now();
            validatePrice();
        }
    }


    /** Getters */

    public ProductId getId() {
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

    public BigDecimal getPrice() {
        return price;
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
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

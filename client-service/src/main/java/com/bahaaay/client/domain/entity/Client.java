package com.bahaaay.client.domain.entity;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.exception.BadRequestException;

import java.time.Instant;

/**
 * Client Aggregate Root Entity.
 * This class is immutable and provides factory methods for creating new instances or loading existing ones.
 */
public class Client {

    private final ClientId id;
    private String name;
    private String lastName;
    private String mobileNumber;

    private final Instant createdAt;
    private Instant updatedAt;

    private Client(ClientId id, String name, String lastName, String mobileNumber, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate();
    }

    /**
     * Factory method to create a new Client instance.
     *
     * @param name         First name of the client.
     * @param lastName     Last name of the client.
     * @param mobileNumber Mobile number of the client.
     * @return A new Client instance.
     */
    public static Client create(String name, String lastName, String mobileNumber) {
        Instant now = Instant.now();
        return new Client(ClientId.generate(), name, lastName, mobileNumber, now, now);
    }

    /**
     * Factory method to load an existing Client instance from storage.
     */
    public static Client load(ClientId id, String name, String lastName, String mobileNumber, Instant createdAt, Instant updatedAt) {
        return new Client(id, name, lastName, mobileNumber, createdAt, updatedAt);
    }

    /** Validation */
    private void validate() {
        validateId();
        validateName();
        validateLastName();
        validateMobileNumber();
    }

    private void validateId() {
        if (id == null) {
            throw new BadRequestException("Client ID cannot be null");
        }
    }

    private void validateName() {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Client name cannot be null or empty");
        }
    }

    private void validateLastName() {
        if (lastName == null || lastName.isBlank()) {
            throw new BadRequestException("Client last name cannot be null or empty");
        }
    }

    private void validateMobileNumber() {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new BadRequestException("Mobile number cannot be null or empty");
        }
    }

    /** Update methods */
    public void updateName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
            this.updatedAt = Instant.now();
            validateName();
        }
    }

    public void updateLastName(String lastName) {
        if (lastName != null && !lastName.isBlank()) {
            this.lastName = lastName;
            this.updatedAt = Instant.now();
            validateLastName();
        }
    }

    public void updateMobileNumber(String mobileNumber) {
        if (mobileNumber != null && !mobileNumber.isBlank()) {
            this.mobileNumber = mobileNumber;
            this.updatedAt = Instant.now();
            validateMobileNumber();
        }
    }

    /** Getters */
    public ClientId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
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
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

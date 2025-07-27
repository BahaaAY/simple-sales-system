package com.bahaaay.sales.domain.entity.client_ref;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;

public class ClientRef {
    private ClientId id;
    private String fullName;
    private String mobileNumber;

    public ClientRef(ClientId clientId, String fullName, String mobileNumber) {
        this.id = clientId;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
    }

    public static ClientRef load(ClientId id, String fullName, String mobileNumber) {
        return new ClientRef(id, fullName, mobileNumber);
    }

    public ClientId getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}

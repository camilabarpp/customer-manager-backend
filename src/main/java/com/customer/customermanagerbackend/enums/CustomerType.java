package com.customer.customermanagerbackend.enums;

import lombok.Getter;

@Getter
public enum CustomerType {
    PF("PF"),
    PJ("PJ");

    private final String value;

    CustomerType(String value) {
        this.value = value;
    }

    public static CustomerType fromValue(String value) {
        for (CustomerType type : CustomerType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown CustomerType value: " + value);
    }
}

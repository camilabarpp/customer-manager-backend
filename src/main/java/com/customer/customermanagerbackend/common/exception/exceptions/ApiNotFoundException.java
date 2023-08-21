package com.customer.customermanagerbackend.common.exception.exceptions;

public class ApiNotFoundException extends RuntimeException {
    public ApiNotFoundException(String message) {
        super(message);
    }
}

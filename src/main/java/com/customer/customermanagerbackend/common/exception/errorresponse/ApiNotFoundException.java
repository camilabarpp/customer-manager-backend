package com.customer.customermanagerbackend.common.exception.errorresponse;

public class ApiNotFoundException extends RuntimeException {
    public ApiNotFoundException(String message) {
        super(message);
    }
}

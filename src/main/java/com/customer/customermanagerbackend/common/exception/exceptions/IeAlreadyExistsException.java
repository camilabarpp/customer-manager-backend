package com.customer.customermanagerbackend.common.exception.exceptions;

public class IeAlreadyExistsException extends RuntimeException {
    public IeAlreadyExistsException() {
        super("IE already exists");
    }
}

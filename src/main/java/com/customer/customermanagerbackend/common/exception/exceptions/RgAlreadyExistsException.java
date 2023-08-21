package com.customer.customermanagerbackend.common.exception.exceptions;

public class RgAlreadyExistsException extends RuntimeException {
    public RgAlreadyExistsException() {
        super("RG already exists");
    }
}

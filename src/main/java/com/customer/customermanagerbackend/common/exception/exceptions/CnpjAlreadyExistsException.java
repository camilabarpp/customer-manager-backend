package com.customer.customermanagerbackend.common.exception.exceptions;

public class CnpjAlreadyExistsException extends RuntimeException {
    public CnpjAlreadyExistsException() {
        super("CNPJ already exists");
    }
}


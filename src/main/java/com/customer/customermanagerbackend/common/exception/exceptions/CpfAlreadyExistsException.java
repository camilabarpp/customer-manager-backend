package com.customer.customermanagerbackend.common.exception.exceptions;

public class CpfAlreadyExistsException  extends RuntimeException {
    public CpfAlreadyExistsException() {
        super("CPF already exists");
    }
}

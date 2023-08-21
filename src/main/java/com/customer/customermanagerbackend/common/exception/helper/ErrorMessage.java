package com.customer.customermanagerbackend.common.exception.helper;

public class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String CUSTOMER_NOT_FOUND = "Customer not found with id: ";
    public static final String CUSTOMER_TYPE_INVALID = "Invalid customer type: ";
    public static final String CPF_IS_INVALID = "CPF is invalid";
    public static final String CNPJ_IS_INVALID = "CNPJ is invalid";
    public static final String CPF_IS_REQUIRED = "CPF is required";
    public static final String CNPJ_IS_REQUIRED = "CNPJ is required";
    public static final String RG_IS_REQUIRED = "RG is required";
    public static final String IE_IS_REQUIRED = "IE is required";
    public static final String NUMBER_IS_REQUIRED = "Number is required";
}
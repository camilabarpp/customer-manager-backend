package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Type is required")
    private CustomerType type;
    private String cpf;
    private String cnpj;
    private String rg;
    private String ie;
    @NotBlank(message = "Registration date is required")
    private List<PhoneNumber> phoneNumbers;
}

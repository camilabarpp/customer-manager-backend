package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class CustomerRequest {
    @NotNull
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

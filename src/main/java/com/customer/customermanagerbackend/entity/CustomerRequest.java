package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private CustomerType type;
    @CPF(message = "CPF is invalid")
    private String cpf;
    @CNPJ(message = "CNPJ is invalid")
    private String cnpj;
    private String rg;
    private String ie;
    private List<PhoneNumber> phoneNumbers;
}

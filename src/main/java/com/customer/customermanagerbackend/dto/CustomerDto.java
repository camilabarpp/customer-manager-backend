package com.customer.customermanagerbackend.dto;

import com.customer.customermanagerbackend.entity.PhoneNumber;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private Long id;
    private String name;
    private String type;
    private String cpf;
    private String rg;
    private String cnpj;
    private String ie;
    private List<PhoneNumber> phoneNumbers;
}

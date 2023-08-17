package com.customer.customermanagerbackend.dto;

import com.customer.customermanagerbackend.entity.PhoneNumber;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private String name;
    private String type;
    private String cpf;  // Para pessoa física (PF)
    private String rg;   // Para pessoa física (PF)
    private String cnpj; // Para pessoa jurídica (PJ)
    private String ie;   // Para pessoa jurídica (PJ)
    private List<PhoneNumber> phoneNumbers;
}

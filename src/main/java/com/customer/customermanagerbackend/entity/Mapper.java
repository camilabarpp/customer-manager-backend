package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.dto.CustomerDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {
    public static CustomerPf customerDtoToCustomerPf(CustomerDto customerDto) {
        CustomerPf customerPf = new CustomerPf();
        customerPf.setName(customerDto.getName());
        customerPf.setPhoneNumbers(customerDto.getPhoneNumbers());
        customerPf.setCpf(customerDto.getCpf());
        customerPf.setRg(customerDto.getRg());
        return customerPf;
    }

    public static CustomerPj customerDtoToCustomerPj(CustomerDto customerDto) {
        CustomerPj customerPj = new CustomerPj();
        customerPj.setName(customerDto.getName());
        customerPj.setPhoneNumbers(customerDto.getPhoneNumbers());
        customerPj.setCnpj(customerDto.getCnpj());
        customerPj.setIe(customerDto.getIe());
        return customerPj;
    }

    public static CustomerDto customerPfToCustomerDto(CustomerPf customerPf) {
        return CustomerDto.builder()
                .name(customerPf.getName())
                .type(customerPf.getType().toString())
                .phoneNumbers(customerPf.getPhoneNumbers())
                .cpf(customerPf.getCpf())
                .rg(customerPf.getRg())
                .build();
    }

    public static CustomerDto customerPjToCustomerDto(CustomerPj customerPj) {
        return CustomerDto.builder()
                .name(customerPj.getName())
                .type(customerPj.getType().toString())
                .phoneNumbers(customerPj.getPhoneNumbers())
                .cnpj(customerPj.getCnpj())
                .ie(customerPj.getIe())
                .build();
    }
}

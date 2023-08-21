package com.customer.customermanagerbackend.model.mapper;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.model.entity.Customer;
import com.customer.customermanagerbackend.model.entity.CustomerPf;
import com.customer.customermanagerbackend.model.entity.CustomerPj;
import com.customer.customermanagerbackend.model.request.CustomerRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public static CustomerPf customerRequestToCustomerPf(CustomerRequest customerRequest) {
        CustomerPf customerPf = new CustomerPf();
        customerPf.setName(customerRequest.getName());
        customerPf.setPhoneNumbers(customerRequest.getPhoneNumbers());
        customerPf.setCpf(customerRequest.getCpf());
        customerPf.setRg(customerRequest.getRg());
        return customerPf;
    }

    public static CustomerPj customerRequestToCustomerPj(CustomerRequest customerRequest) {
        CustomerPj customerPj = new CustomerPj();
        customerPj.setName(customerRequest.getName());
        customerPj.setPhoneNumbers(customerRequest.getPhoneNumbers());
        customerPj.setCnpj(customerRequest.getCnpj());
        customerPj.setIe(customerRequest.getIe());
        return customerPj;
    }

    public static CustomerDto customerRequestToCustomerDto(CustomerRequest customerRequest) {
        return CustomerDto.builder()
                .name(customerRequest.getName())
                .type(customerRequest.getType().toString())
                .phoneNumbers(customerRequest.getPhoneNumbers())
                .cpf(customerRequest.getCpf())
                .rg(customerRequest.getRg())
                .cnpj(customerRequest.getCnpj())
                .ie(customerRequest.getIe())
                .build();
    }

    public static Customer customerDtoToCustomer(CustomerDto customerDto) {
        if (customerDto.getType().equals("PF")) {
            return customerDtoToCustomerPf(customerDto);
        } else {
            return customerDtoToCustomerPj(customerDto);
        }
    }



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
                .id(customerPf.getId())
                .name(customerPf.getName())
                .type(customerPf.getType().toString())
                .phoneNumbers(customerPf.getPhoneNumbers())
                .cpf(customerPf.getCpf())
                .rg(customerPf.getRg())
                .build();
    }

    public static CustomerDto customerPjToCustomerDto(CustomerPj customerPj) {
        return CustomerDto.builder()
                .id(customerPj.getId())
                .name(customerPj.getName())
                .type(customerPj.getType().toString())
                .phoneNumbers(customerPj.getPhoneNumbers())
                .cnpj(customerPj.getCnpj())
                .ie(customerPj.getIe())
                .build();
    }
}

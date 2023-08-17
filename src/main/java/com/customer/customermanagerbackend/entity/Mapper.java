package com.customer.customermanagerbackend.entity;

import com.customer.customermanagerbackend.dto.CustomerDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public static CustomerPf customerToPf(Customer customer) {
        CustomerPf customerPf = new CustomerPf();
        customerPf.setId(customer.getId());
        customerPf.setName(customer.getName());
        customerPf.setPhoneNumbers(customer.getPhoneNumbers());
//        customerPf.setCpf(customer.getCpf());
//        customerPf.setRg(customer.getRg());
        return customerPf;
    }

    public static CustomerPj customerToPj(Customer customer) {
        CustomerPj customerPj = new CustomerPj();
        customerPj.setId(customer.getId());
        customerPj.setName(customer.getName());
        customerPj.setPhoneNumbers(customer.getPhoneNumbers());
//        customerPj.setCnpj(customer.getCnpj());
//        customerPj.setIe(customer.getIe());
        return customerPj;
    }

    public static Customer pfToCustomer(CustomerPf customerPf) {
        Customer customer = new Customer();
        customer.setId(customerPf.getId());
        customer.setName(customerPf.getName());
        customer.setPhoneNumbers(customerPf.getPhoneNumbers());
//        customer.setCpf(customerPf.getCpf());
//        customer.setRg(customerPf.getRg());
        return customer;
    }

    public static Customer pjToCustomer(CustomerPj customerPj) {
        Customer customer = new Customer();
        customer.setId(customerPj.getId());
        customer.setName(customerPj.getName());
        customer.setPhoneNumbers(customerPj.getPhoneNumbers());
//        customer.setCnpj(customerPj.getCnpj());
//        customer.setIe(customerPj.getIe());
        return customer;
    }

    public static Customer customerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setPhoneNumbers(customerDto.getPhoneNumbers());
        return customer;
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

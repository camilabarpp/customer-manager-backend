package com.customer.customermanagerbackend.service;

import com.customer.customermanagerbackend.common.exception.exceptions.*;
import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.enums.CustomerType;
import com.customer.customermanagerbackend.repository.CustomerPfRepository;
import com.customer.customermanagerbackend.repository.CustomerPjRepository;
import com.customer.customermanagerbackend.repository.CustomerRepository;
import com.customer.customermanagerbackend.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import com.customer.customermanagerbackend.model.entity.Customer;
import com.customer.customermanagerbackend.model.entity.CustomerPf;
import com.customer.customermanagerbackend.model.entity.CustomerPj;
import com.customer.customermanagerbackend.model.request.CustomerRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CUSTOMER_NOT_FOUND;
import static com.customer.customermanagerbackend.common.exception.helper.ErrorMessage.CUSTOMER_TYPE_INVALID;
import static com.customer.customermanagerbackend.model.mapper.Mapper.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPfRepository customerPfRepository;
    private final CustomerPjRepository customerPjRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ApiNotFoundException(CUSTOMER_NOT_FOUND + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public CustomerDto createCustomer(CustomerRequest customerRequest) {

        CustomerType type = CustomerType.valueOf(customerRequest.getType().toString());

        if (type == CustomerType.PF) {
            return customerPfToCustomerDto(createCustomerPf(customerRequestToCustomerPf(customerRequest)));
        } else if (type == CustomerType.PJ) {
            return customerPjToCustomerDto(createCustomerPj(customerRequestToCustomerPj(customerRequest)));
        } else {
            throw new IllegalArgumentException(CUSTOMER_TYPE_INVALID + type);
        }
    }

    public CustomerPf createCustomerPf(CustomerPf customerPf) {
        var cpf = customerPfRepository.existsByCpf(customerPf.getCpf());
        var rg = customerPfRepository.existsByRg(customerPf.getRg());

        if (cpf) {
            throw new CpfAlreadyExistsException();
        } else if (rg) {
            throw new RgAlreadyExistsException();
        } else {
            customerPf.setRegistrationDate(LocalDate.now());
            phoneNumberRepository.saveAll(customerPf.getPhoneNumbers());
            return customerPfRepository.save(customerPf);
        }
    }

    public CustomerPj createCustomerPj(CustomerPj customerPj) {
        var cnpj = customerPjRepository.existsByCnpj(customerPj.getCnpj());
        var ie = customerPjRepository.existsByIe(customerPj.getIe());

        if (cnpj) {
            throw new CnpjAlreadyExistsException();
        } else if (ie) {
            throw new IeAlreadyExistsException();
        } else {
            customerPj.setRegistrationDate(LocalDate.now());
            phoneNumberRepository.saveAll(customerPj.getPhoneNumbers());
            return customerPjRepository.save(customerPj);
        }
    }

    public Customer updateCustomerPj(Long id, CustomerRequest customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update Customer properties
            customer.setName(customerDto.getName());
            customer.setPhoneNumbers(customerDto.getPhoneNumbers());

            // Save updated Customer and PhoneNumbers
            phoneNumberRepository.saveAll(customerDto.getPhoneNumbers());
            customerRepository.save(customer);

            // Save updated Customer and PhoneNumbers
            customerRepository.save(customer);

            // Update CustomerPf properties
            Optional<CustomerPj> optionalCustomerPj = customerPjRepository.findById(id);
            optionalCustomerPj.ifPresent(customerPj -> {
                customerPj.setCnpj(customerDto.getCnpj());
                customerPj.setIe(customerDto.getIe());
                customerPjRepository.save(customerPj);
            });

            return customer;
        } else {
            throw new ApiNotFoundException(CUSTOMER_NOT_FOUND + id);
        }
    }


    public Customer updateCustomerPf(Long id, CustomerRequest customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update Customer properties
            customer.setName(customerDto.getName());
            customer.setPhoneNumbers(customerDto.getPhoneNumbers());

            // Save updated Customer and PhoneNumbers
            phoneNumberRepository.saveAll(customerDto.getPhoneNumbers());
            customerRepository.save(customer);

            // Update CustomerPf properties
            Optional<CustomerPf> optionalCustomerPf = customerPfRepository.findById(id);
            optionalCustomerPf.ifPresent(customerPf -> {
                customerPf.setCpf(customerDto.getCpf());
                customerPf.setRg(customerDto.getRg());
                customerPfRepository.save(customerPf);
            });

            return customer;
        } else {
            throw new ApiNotFoundException(CUSTOMER_NOT_FOUND + id);
        }
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}

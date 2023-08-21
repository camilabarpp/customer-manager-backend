package com.customer.customermanagerbackend.service;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.entity.*;
import com.customer.customermanagerbackend.enums.CustomerType;
import com.customer.customermanagerbackend.repository.CustomerPfRepository;
import com.customer.customermanagerbackend.repository.CustomerPjRepository;
import com.customer.customermanagerbackend.repository.CustomerRepository;
import com.customer.customermanagerbackend.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.customer.customermanagerbackend.entity.Mapper.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPfRepository customerPfRepository;
    private final CustomerPjRepository customerPjRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer by id " + id + " was not found"));
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
            throw new IllegalArgumentException("Invalid customer type: " + type);
        }
    }

    public CustomerPf createCustomerPf(CustomerPf customerPf) {
        var cpf = customerPfRepository.existsByCpf(customerPf.getCpf());
        var rg = customerPfRepository.existsByRg(customerPf.getRg());

        if (cpf) {
            throw new RuntimeException("CPF already exists");
        } else if (rg) {
            throw new RuntimeException("RG already exists");
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
            throw new RuntimeException("CNPJ already exists");
        } else if (ie) {
            throw new RuntimeException("IE already exists");
        } else {
            customerPj.setRegistrationDate(LocalDate.now());
            phoneNumberRepository.saveAll(customerPj.getPhoneNumbers());
            return customerPjRepository.save(customerPj);
        }
    }

    public Customer updateCustomer(Long id, CustomerRequest customerDto) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDto.getName());
                    customer.setPhoneNumbers(customerDto.getPhoneNumbers());
                    if (customerDto.getType().equals("PF")) {
                        CustomerPf customerPf = customerRequestToCustomerPf(customerDto);
                        customerPf.setId(customer.getId());
                        customerPf.setCpf(customerDto.getCpf());
                        customerPf.setRg(customerDto.getRg());
                        customerPfRepository.save(customerPf);
                    } else {
                        CustomerPj customerPj = customerRequestToCustomerPj(customerDto);
                        customerPj.setCnpj(customerDto.getCnpj());
                        customerPj.setIe(customerDto.getIe());
                        customerPjRepository.save(customerPj);
                    }
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer by id " + id + " was not found"));
    }

    public Customer updateCustomerPj(Long id, CustomerRequest customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Update Customer properties
            customer.setName(customerDto.getName());

            var phones = customer.getPhoneNumbers();
            phones.addAll(customerDto.getPhoneNumbers());
            phoneNumberRepository.saveAll(phones);

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
            throw new RuntimeException("Customer by id " + id + " was not found");
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
            customerRepository.save(customer);
            phoneNumberRepository.saveAll(customerDto.getPhoneNumbers());

            // Update CustomerPf properties
            Optional<CustomerPf> optionalCustomerPf = customerPfRepository.findById(id);
            optionalCustomerPf.ifPresent(customerPf -> {
                customerPf.setCpf(customerDto.getCpf());
                customerPf.setRg(customerDto.getRg());
                customerPfRepository.save(customerPf);
            });

            return customer;
        } else {
            throw new RuntimeException("Customer by id " + id + " was not found");
        }
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

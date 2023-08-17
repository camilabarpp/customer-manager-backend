package com.customer.customermanagerbackend.service;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.entity.Customer;
import com.customer.customermanagerbackend.entity.CustomerPf;
import com.customer.customermanagerbackend.entity.CustomerPj;
import com.customer.customermanagerbackend.entity.CustomerRequest;
import com.customer.customermanagerbackend.enums.CustomerType;
import com.customer.customermanagerbackend.repository.CustomerPfRepository;
import com.customer.customermanagerbackend.repository.CustomerPjRepository;
import com.customer.customermanagerbackend.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.customer.customermanagerbackend.entity.Mapper.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPfRepository customerPfRepository;
    private final CustomerPjRepository customerPjRepository;

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
        customerPf.setRegistrationDate(LocalDate.now());
        return customerPfRepository.save(customerPf);
    }

    public CustomerPj createCustomerPj(CustomerPj customerPj) {
        customerPj.setRegistrationDate(LocalDate.now());
        return customerPjRepository.save(customerPj);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = getCustomerById(id);
        CustomerType type = CustomerType.valueOf(customerDto.getType());

        if (type == CustomerType.PF) {
            return customerPfToCustomerDto(updateCustomerPf(customerDtoToCustomerPf(customerDto)));
        } else if (type == CustomerType.PJ) {
            return customerPjToCustomerDto(updateCustomerPj(customerDtoToCustomerPj(customerDto)));
        } else {
            throw new IllegalArgumentException("Invalid customer type: " + type);
        }
    }

    public CustomerPf updateCustomerPf(CustomerPf customerPf) {
        return customerPfRepository.save(customerPf);
    }

    public CustomerPj updateCustomerPj(CustomerPj customerPj) {
        return customerPjRepository.save(customerPj);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

package com.customer.customermanagerbackend.service;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.entity.Customer;
import com.customer.customermanagerbackend.entity.CustomerPf;
import com.customer.customermanagerbackend.entity.CustomerPj;
import com.customer.customermanagerbackend.enums.CustomerType;
import com.customer.customermanagerbackend.repository.CustomerPfRepository;
import com.customer.customermanagerbackend.repository.CustomerPjRepository;
import com.customer.customermanagerbackend.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Query("SELECT c FROM Customer c WHERE c.isActive = true")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {

        CustomerType type = CustomerType.valueOf(customerDto.getType());
        if (type == null) {
            throw new IllegalArgumentException("Customer type cannot be null");
        }

        if (type == CustomerType.PF) {
            return customerPfToCustomerDto(createCustomerPf(customerDtoToCustomerPf(customerDto)));
        } else if (type == CustomerType.PJ) {
            return customerPjToCustomerDto(createCustomerPj(customerDtoToCustomerPj(customerDto)));
        } else {
            throw new IllegalArgumentException("Invalid customer type: " + type);
        }
    }

    // Create a CustomerPf
    public CustomerPf createCustomerPf(CustomerPf customerPf) {
        customerPf.setRegistrationDate(LocalDate.now());
        return customerPfRepository.save(customerPf);
    }

    // Create a CustomerPj
    public CustomerPj createCustomerPj(CustomerPj customerPj) {
        customerPj.setRegistrationDate(LocalDate.now());
        return customerPjRepository.save(customerPj);
    }
}

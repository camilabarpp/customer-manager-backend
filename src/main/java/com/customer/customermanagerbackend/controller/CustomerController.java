package com.customer.customermanagerbackend.controller;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.entity.Customer;
import com.customer.customermanagerbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }
}

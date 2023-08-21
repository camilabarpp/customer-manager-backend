package com.customer.customermanagerbackend.controller;

import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.entity.Customer;
import com.customer.customermanagerbackend.entity.CustomerRequest;
import com.customer.customermanagerbackend.entity.Mapper;
import com.customer.customermanagerbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

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

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name) {
        List<Customer> customers = customerService.getCustomersByName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerDto createdCustomer = customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerDto) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping("pj/{id}")
    public ResponseEntity<Customer> updateCustomerPj(@PathVariable Long id, @RequestBody CustomerRequest customerDto) {
        Customer updatedCustomer = customerService.updateCustomerPj(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping("pf/{id}")
    public ResponseEntity<Customer> updateCustomerPf(@PathVariable Long id, @RequestBody CustomerRequest customerDto) {
        Customer updatedCustomer = customerService.updateCustomerPf(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}

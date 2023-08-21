package com.customer.customermanagerbackend.service;

import com.customer.customermanagerbackend.common.exception.exceptions.*;
import com.customer.customermanagerbackend.dto.CustomerDto;
import com.customer.customermanagerbackend.enums.CustomerType;
import com.customer.customermanagerbackend.model.entity.Customer;
import com.customer.customermanagerbackend.model.entity.CustomerPf;
import com.customer.customermanagerbackend.model.entity.CustomerPj;
import com.customer.customermanagerbackend.model.entity.PhoneNumber;
import com.customer.customermanagerbackend.model.mapper.Mapper;
import com.customer.customermanagerbackend.model.request.CustomerRequest;
import com.customer.customermanagerbackend.repository.CustomerPfRepository;
import com.customer.customermanagerbackend.repository.CustomerPjRepository;
import com.customer.customermanagerbackend.repository.CustomerRepository;
import com.customer.customermanagerbackend.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerPfRepository customerPfRepository;

    @Mock
    private CustomerPjRepository customerPjRepository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    @DisplayName("Should return all customers")
    void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> customersReturned = customerService.getAllCustomers();

        assertEquals(customers, customersReturned);
    }

    @Test
    @DisplayName("Should return a customer by id")
    void getCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        Customer customerReturned = customerService.getCustomerById(1L);

        assertEquals(customer, customerReturned);
    }

    @Test
    @DisplayName("Should throw an exception when customer is not found")
    void getCustomerByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApiNotFoundException.class, () -> customerService.getCustomerById(1L));
    }


    @Test
    @DisplayName("Should return all customers by name")
    void getCustomersByName() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findByNameContaining("Customer")).thenReturn(customers);

        List<Customer> customersReturned = customerService.getCustomersByName("Customer");

        assertEquals(customers, customersReturned);
    }

    @Test
    @DisplayName("Should create a pf customer successfully")
    void testCreateCustomerPf_Success() {
        CustomerPf customerPf = new CustomerPf();
        customerPf.setCpf("012151654");
        customerPf.setRg("5465564465");

        when(customerPfRepository.existsByCpf("012151654")).thenReturn(false);
        when(customerPfRepository.existsByRg("5465564465")).thenReturn(false);
        when(customerPfRepository.save(any(CustomerPf.class))).thenReturn(customerPf);

        CustomerPf result = customerService.createCustomerPf(customerPf);

        verify(phoneNumberRepository).saveAll(customerPf.getPhoneNumbers());
        verify(customerPfRepository).save(customerPf);

        assertEquals("012151654", result.getCpf());
        assertEquals("5465564465", result.getRg());
    }

    @Test
    @DisplayName("Should create a pj customer successfully")
    void testCreateCustomerPj_Success() {
        CustomerPj customerPj = new CustomerPj();
        customerPj.setCnpj("012151654");
        customerPj.setIe("5465564465");

        when(customerPjRepository.existsByCnpj("012151654")).thenReturn(false);
        when(customerPjRepository.existsByIe("5465564465")).thenReturn(false);
        when(customerPjRepository.save(any(CustomerPj.class))).thenReturn(customerPj);

        CustomerPj result = customerService.createCustomerPj(customerPj);

        verify(phoneNumberRepository).saveAll(customerPj.getPhoneNumbers());
        verify(customerPjRepository).save(customerPj);

        assertEquals("012151654", result.getCnpj());
        assertEquals("5465564465", result.getIe());
    }

    @Test
    @DisplayName("Should throw CpfAlreadyExistsException when cpf already exists")
    void testCreateCustomerPf_CpfAlreadyExists() {
        when(customerPfRepository.save(any(CustomerPf.class))).thenReturn(customerPf);
        CustomerPf newCustomerPf = new CustomerPf();
        newCustomerPf.setCpf("012151654");

        when(customerPfRepository.save(any(CustomerPf.class))).thenReturn(customerPf);
        when(customerPfRepository.existsByCpf("012151654")).thenReturn(true);

        assertThrows(CpfAlreadyExistsException.class,
                () -> customerService.createCustomerPf(newCustomerPf));
    }

    @Test
    @DisplayName("Should throw RgAlreadyExistsException when rg already exists")
    void testCreateCustomerPf_RgAlreadyExists() {
        when(customerPfRepository.save(any(CustomerPf.class))).thenReturn(customerPf);
        CustomerPf newCustomerPf = new CustomerPf();
        newCustomerPf.setRg("5465564465");

        when(customerPfRepository.save(any(CustomerPf.class))).thenReturn(customerPf);
        when(customerPfRepository.existsByRg("5465564465")).thenReturn(true);

        assertThrows(RgAlreadyExistsException.class,
                () -> customerService.createCustomerPf(newCustomerPf));
    }

    @Test
    @DisplayName("Should throw CnpjAlreadyExistsException when cnpj already exists")
    void testCreateCustomerPj_CnpjAlreadyExists() {
        when(customerPjRepository.save(any(CustomerPj.class))).thenReturn(customerPj);
        CustomerPj newCustomerPj = new CustomerPj();
        newCustomerPj.setCnpj("012151654");

        when(customerPjRepository.save(any(CustomerPj.class))).thenReturn(customerPj);
        when(customerPjRepository.existsByCnpj("012151654")).thenReturn(true);

        assertThrows(CnpjAlreadyExistsException.class,
                () -> customerService.createCustomerPj(newCustomerPj));
    }

    @Test
    @DisplayName("Should throw IeAlreadyExistsException when ie already exists")
    void testCreateCustomerPj_IeAlreadyExists() {
        when(customerPjRepository.save(any(CustomerPj.class))).thenReturn(customerPj);
        CustomerPj newCustomerPj = new CustomerPj();
        newCustomerPj.setIe("5465564465");

        when(customerPjRepository.save(any(CustomerPj.class))).thenReturn(customerPj);
        when(customerPjRepository.existsByIe("5465564465")).thenReturn(true);

        assertThrows(IeAlreadyExistsException.class,
                () -> customerService.createCustomerPj(newCustomerPj));
    }

    @Test
    @DisplayName("Should throw an exception when pf customer is not found")
    void testUpdatePfCustomerNotFound() {
        CustomerRequest customerRequest = Mapper.customerPfToCustomerRequest(customerPf);

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApiNotFoundException.class, () -> customerService.updateCustomerPf(1L, customerRequest));
    }

    @Test
    @DisplayName("Should throw an exception when pj customer is not found")
    void testUpdatePjCustomerNotFound() {
        CustomerRequest customerRequest = Mapper.customerPjToCustomerRequest(customerPj);

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApiNotFoundException.class, () -> customerService.updateCustomerPj(1L, customerRequest));
    }

    @Test
    @DisplayName("Should update a pj customer successfully")
    void testUpdateCustomerPj() {
        customerPj.setPhoneNumbers(List.of(phoneNumber1));

        CustomerRequest customerRequest = Mapper.customerPjToCustomerRequest(customerPj);

        Mockito.when(customerPjRepository.existsByCnpj(any())).thenReturn(false);
        Mockito.when(customerPjRepository.existsByIe(any())).thenReturn(false);
        Mockito.when(customerPjRepository.save(any())).thenReturn(customerPj);

        CustomerDto customerDto = customerService.createCustomer(customerRequest);

        assertEquals(customerPj.getCnpj(), customerDto.getCnpj());
        assertEquals(customerPj.getIe(), customerDto.getIe());
    }

    @Test
    @DisplayName("Should update a pf customer successfully")
    void testUpdateCustomerPf() {
        customerPf.setPhoneNumbers(List.of(phoneNumber1));

        CustomerRequest customerRequest = Mapper.customerPfToCustomerRequest(customerPf);

        Mockito.when(customerPfRepository.existsByCpf(any())).thenReturn(false);
        Mockito.when(customerPfRepository.existsByRg(any())).thenReturn(false);
        Mockito.when(customerPfRepository.save(any())).thenReturn(customerPf);

        CustomerDto customerDto = customerService.createCustomer(customerRequest);

        assertEquals(customerPf.getCpf(), customerDto.getCpf());
        assertEquals(customerPf.getRg(), customerDto.getRg());
    }

    @Test
    @DisplayName("Should delete a customer successfully")
    void testDeleteCustomer() {
        customerService.createCustomerPf(customerPf);
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        customerService.deleteCustomer(1L);

        verify(customerRepository).deleteById(1L);
        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    @DisplayName("Should delete all customers successfully")
    void testDeleteAllCustomers() {
        customerService.createCustomerPf(customerPf);

        customerService.deleteAllCustomers();

        verify(customerRepository).deleteAll();
        assertEquals(0, customerRepository.findAll().size());
    }

    Customer customer = Customer.builder()
            .name("Customer")
            .isActive(true)
            .phoneNumbers(new ArrayList<>())
            .build();

    CustomerPf customerPf = new CustomerPf("012151654", "5465564465",CustomerType.PF ,LocalDate.now());

    PhoneNumber phoneNumber1 = PhoneNumber.builder()
            .number("123456789")
            .build();

    CustomerPj customerPj = new CustomerPj("012151654", "5465564465",CustomerType.PJ ,LocalDate.now());

}
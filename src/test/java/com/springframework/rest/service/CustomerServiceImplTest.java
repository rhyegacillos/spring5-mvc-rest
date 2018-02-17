package com.springframework.rest.service;

import com.springframework.rest.api.v1.mapper.CustomerMapper;
import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.domain.Customer;
import com.springframework.rest.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Andrew");
        customer1.setLastname("Garfield");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Andrew");
        customer2.setLastname("Garfield");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getCustomerById() {

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Andrew");
        customer1.setLastname("Garfield");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        CustomerDTO customerDTO = customerService.getCustomerById(customer1.getId());

        assertEquals("Andrew", customerDTO.getFirstname());
    }

    @Test
    public void testCreateNewCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Rhye");

        Customer customerSaved = new Customer();
        customerSaved.setFirstname(customerDTO.getFirstname());
        customerSaved.setLastname(customerDTO.getLastname());
        customerSaved.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customerSaved);

        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        assertEquals(savedDTO.getFirstname(), customerSaved.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());

    }

    @Test
    public void testUpdateCustomerByDTO() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO returnDTO = customerService.updateCustomerByDTO(1L, customerDTO);

        assertEquals(returnDTO.getFirstname(), customer.getFirstname());
        assertEquals(returnDTO.getId(), customer.getId());
    }
}
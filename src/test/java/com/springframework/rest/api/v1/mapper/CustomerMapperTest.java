package com.springframework.rest.api.v1.mapper;

import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    private static final String NAME = "rhye";

    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstname(NAME);
        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
        assertEquals(customer.getFirstname(), customerDTO.getFirstname());
    }

    @Test
    public void customerDtoToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);
        Customer customer = mapper.customerDtoToCustomer(customerDTO);
        assertEquals(customer.getFirstname(), customerDTO.getFirstname());
    }
}
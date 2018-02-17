package com.springframework.rest.service;

import com.springframework.rest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}

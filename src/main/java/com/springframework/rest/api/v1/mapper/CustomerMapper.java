package com.springframework.rest.api.v1.mapper;

import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.domain.Customer;
import com.springframework.rest.service.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = CustomerService.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}

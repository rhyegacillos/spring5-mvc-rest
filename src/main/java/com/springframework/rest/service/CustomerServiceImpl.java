package com.springframework.rest.service;

import com.springframework.rest.api.v1.mapper.CustomerMapper;
import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.domain.Customer;
import com.springframework.rest.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new); //  todo implement better exception handling
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return savedAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return savedAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstname() != null) {
                customer.setFirstname(customerDTO.getFirstname());
            }

            if (customerDTO.getLastname() != null) {
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnDTO.setCustomerUrl("/api/v1/customer/" + id);
            return returnDTO;
        }).orElseThrow(RuntimeException::new);

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO savedAndReturnDTO(Customer customer) {
        Customer customerSaved = customerRepository.save(customer);

        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerSaved);
        returnDTO.setCustomerUrl("/api/v1/customers/" + customerSaved.getId());

        return returnDTO;
    }


}

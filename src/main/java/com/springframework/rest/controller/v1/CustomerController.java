package com.springframework.rest.controller.v1;

import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.api.v1.model.CustomerListDTO;
import com.springframework.rest.controller.AbstractRestControllerTest;
import com.springframework.rest.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
@AllArgsConstructor
public class CustomerController extends AbstractRestControllerTest {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

}

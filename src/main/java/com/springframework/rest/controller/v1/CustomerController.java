package com.springframework.rest.controller.v1;

import com.springframework.rest.api.v1.model.CustomerDTO;
import com.springframework.rest.api.v1.model.CustomerListDTO;
import com.springframework.rest.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController extends AbstractRestControllerTest {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

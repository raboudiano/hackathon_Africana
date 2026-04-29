package com.Shadows.SpringZ.api;

import com.Shadows.SpringZ.api.dto.CustomerRequest;
import com.Shadows.SpringZ.api.dto.CustomerResponse;
import com.Shadows.SpringZ.model.Customer;
import com.Shadows.SpringZ.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON endpoints for Customer CRUD.
 */
@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return customerService.getAllCustomers().stream()
                .map(CustomerApiController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable Long id) {
        return toResponse(customerService.getCustomerByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
        Customer customer = new Customer();
        apply(customer, request);
        Customer saved = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse update(@PathVariable Long id, @RequestBody CustomerRequest request) {
        Customer existing = customerService.getCustomerByID(id);
        apply(existing, request);
        return toResponse(customerService.updateCustomer(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    private static void apply(Customer customer, CustomerRequest request) {
        customer.setName(request.name());
        customer.setSalary(request.salary());
        customer.setPhone(request.phone());
        customer.setAge(request.age());
        customer.setEmail(request.email());
        customer.setPassword(request.password());
        customer.setAddress(request.address());
        customer.setCity(request.city());
    }

    private static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getSalary(),
                customer.getPhone(),
                customer.getAge(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getCity()
        );
    }
}

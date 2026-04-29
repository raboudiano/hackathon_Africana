package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Customer;

import java.util.List;

/**
 * Service Layer for Customer CRUD.
 */
public interface CustomerService {
    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerByID(Long id);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}

package com.fit.se.service;

import com.fit.se.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void deleteCustomerById(int id);

    Customer updateCustomerById(int id, Customer customer);

}

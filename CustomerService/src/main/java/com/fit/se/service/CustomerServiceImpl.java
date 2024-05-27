package com.fit.se.service;

import com.fit.se.entity.Customer;
import com.fit.se.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private RestTemplate restTemplate;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomerById(int id, Customer customer) {
        Customer tempCustomer = customerRepository.findById(id).get();
        tempCustomer.setFirstName(customer.getFirstName());
        tempCustomer.setFirstName(customer.getLastName());
        tempCustomer.setAge(customer.getAge());
        tempCustomer.setGender(customer.getGender());
        tempCustomer.setEmail(customer.getEmail());
        tempCustomer.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(tempCustomer);
    }
}

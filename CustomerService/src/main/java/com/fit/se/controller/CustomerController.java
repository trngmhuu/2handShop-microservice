package com.fit.se.controller;

import com.fit.se.entity.Customer;
import com.fit.se.repository.CustomerRedisRepository;
import com.fit.se.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;
    @Autowired
    private CustomerRedisRepository customerRedisRepository;
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        customerRedisRepository.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerRedisRepository.findAll();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        Customer customer = customerRedisRepository.findById(id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable int id) {
        customerService.deleteCustomerById(id);
        customerRedisRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomerById(@PathVariable int id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomerById(id, customer);
        customerRedisRepository.update(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
}

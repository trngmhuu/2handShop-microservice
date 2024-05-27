package com.fit.se.repository;

import com.fit.se.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public CustomerRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveCustomer(Customer customer) {
        hashOperations.put("CUSTOMER", customer.getId(), customer);
    }

    public List<Customer> findAll() {
        return hashOperations.values("CUSTOMER");
    }

    //@Cacheable(value = "customers", key = "#id")
    public Customer findById(int id) {
        return (Customer) hashOperations.get("CUSTOMER", id);
    }

    public void update(Customer customer) {
        hashOperations.put("CUSTOMER", customer.getId(), customer);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("CUSTOMER", id);
    }

}

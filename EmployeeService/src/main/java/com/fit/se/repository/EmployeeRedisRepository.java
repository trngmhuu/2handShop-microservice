package com.fit.se.repository;


import com.fit.se.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public EmployeeRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }

    public List<Employee> findAll() {
        return hashOperations.values("EMPLOYEE");
    }

    //@Cacheable(value = "employees", key = "#id")
    public Employee findById(int id) {
        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("EMPLOYEE", id);
    }

}

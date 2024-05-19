package com.fit.se.repository;

import com.fit.se.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public DepartmentRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveDepartment(Department department) {
        hashOperations.put("DEPARTMENT", department.getId(), department);
    }

    public List<Department> findAll() {
        return hashOperations.values("DEPARTMENT");
    }

    //@Cacheable(value = "departments", key = "#id")
    public Department findById(int id) {
        return (Department) hashOperations.get("DEPARTMENT", id);
    }

    public void update(Department department) {
        hashOperations.put("DEPARTMENT", department.getId(), department);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("DEPARTMENT", id);
    }

}

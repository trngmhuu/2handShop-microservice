package com.fit.se.repository;


import com.fit.se.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public OrderRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveOrder(Order order) {
        hashOperations.put("ORDER", order.getId(), order);
    }

    public List<Order> findAll() {
        return hashOperations.values("ORDER");
    }

    //@Cacheable(value = "orders", key = "#id")
    public Order findById(int id) {
        return (Order) hashOperations.get("ORDER", id);
    }

    public void update(Order order) {
        hashOperations.put("ORDER", order.getId(), order);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("ORDER", id);
    }

}

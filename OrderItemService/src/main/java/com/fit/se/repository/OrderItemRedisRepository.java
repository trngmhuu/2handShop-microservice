package com.fit.se.repository;

import com.fit.se.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public OrderItemRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveOrderItem(OrderItem orderItem) {
        hashOperations.put("ORDERITEM", orderItem.getId(), orderItem);
    }

    public List<OrderItem> findAll() {
        return hashOperations.values("ORDERITEM");
    }

    //@Cacheable(value = "users", key = "#id")
    public OrderItem findById(int id) {
        return (OrderItem) hashOperations.get("ORDERITEM", id);
    }

    public void update(OrderItem orderItem) {
        hashOperations.put("ORDERITEM", orderItem.getId(), orderItem);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("ORDERITEM", id);
    }

}

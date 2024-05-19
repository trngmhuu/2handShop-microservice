package com.fit.se.repository;

import com.fit.se.entity.Clothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClothingRedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    @Autowired
    public ClothingRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }

    public void saveClothing(Clothing clothing) {
        hashOperations.put("CLOTHING", clothing.getId(), clothing);
    }

    public List<Clothing> findAll() {
        return hashOperations.values("CLOTHING");
    }

    //@Cacheable(value = "clothings", key = "#id")
    public Clothing findById(int id) {
        return (Clothing) hashOperations.get("CLOTHING", id);
    }

    public void update(Clothing clothing) {
        hashOperations.put("CLOTHING", clothing.getId(), clothing);
    }

    public void deleteById(Integer id) {
        hashOperations.delete("CLOTHING", id);
    }

}

package com.sekurity.sek.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisValueService {

    private final ValueOperations<String, Object> valueOperations;

    public RedisValueService(RedisTemplate<String, Object> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    //  Insert and Update
    public void cacheWithExperation(String key, Object data){
        valueOperations.set(key, data, 5, TimeUnit.MINUTES);
    }

    //  Insert and Update
    public void cache(String key, Object data){
        valueOperations.set(key, data);
    }

    //  Get from cache
    public Object getCache(String key){
        return valueOperations.get(key);
    }

    //  Delete from cache
    public void deleteCache(String key){
        valueOperations.getOperations().delete(key);
    }
}

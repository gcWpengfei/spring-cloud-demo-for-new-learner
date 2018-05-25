package com.example.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.service.RedisService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author li gang
 */
@Service
public class RedisServiceImpl implements RedisService {

    public static final String EXCEPTION_KEY = "hystrix";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getInformationException")
    public String getInformationByKey(final String key) {
        if (RedisServiceImpl.EXCEPTION_KEY.equals(key)) {
            throw new RuntimeException("has a runtimeException...");
        }
        return this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void saveInformation( String key,  String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public String getInformationException(final String key) {
        return "has a RuntimeException..." + key;
    }

}

package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author li gang
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getJedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getJedisConnectionFactory() {
        final JedisConnectionFactory factory = new JedisConnectionFactory();
        final JedisPoolConfig config = this.getJedisPoolConfig();
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean
    public StringRedisTemplate getStringRedisTemplate() {
        return new StringRedisTemplate(this.getJedisConnectionFactory());
    }
}

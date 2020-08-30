package com.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Goods;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    //改变默认的序列化机制 使用json序列化
    @Bean
    public RedisTemplate<Object, Goods> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Goods> template = new RedisTemplate<Object, Goods>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Goods> ser = new Jackson2JsonRedisSerializer<Goods>(Goods.class);
        template.setDefaultSerializer(ser);
        return template;
    }


    /**
     * 自定义缓存管理器（Json序列化）
     * 如果没有配置，则使用默认的jdk序列化
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisSerializer.setObjectMapper(objectMapper);
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration).build();

        return redisCacheManager;
    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//        //设置缓存过期时间
//        Map<String, Long> expires = new HashMap<>();
//        expires.put("12h", 3600 * 12L);
//        expires.put("1h", 3600 * 1L);
//        expires.put("10m", 60 * 10L);
//        rcm.setExpires(expires);
////        rcm.setDefaultExpiration(60 * 60 * 12);//默认过期时间
//        return rcm;
//    }
}

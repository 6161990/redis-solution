package com.yoon.demo.config;

import com.yoon.demo.entity.StockRedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

@Profile("local")
@Configuration
@RequiredArgsConstructor
public class RedisLocalSetter {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    private void setStock(StockRedisEntity stock){
        /** org.springframework.data.redis.core.StringRedisTemplate 사용해도됨 */
        redisTemplate.opsForSet().add("productItem", stock);
        // opsForZSet 사용하는 것도 나쁘지 않을 듯. productItem 별로 재고를 관리할 거니까.
        // 어떤 구조로 재고 관리를 하면 좋을지 생각해보자.
    }
}

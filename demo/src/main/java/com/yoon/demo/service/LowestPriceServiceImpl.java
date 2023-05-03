package com.yoon.demo.service;

import com.yoon.demo.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Set getZsetValue(String key) {
        return redisTemplate.opsForZSet().rangeByScore(key, 0, 10);
    }

    @Override
    public int setNewProduct(Product product) {
        redisTemplate.opsForZSet().add(product.getProdGrpId(), product.getProductId(), product.getPrice()); /** key, value , score */
        return redisTemplate.opsForZSet().rank(product.getProdGrpId(), product.getProductId()).intValue();
    }
}

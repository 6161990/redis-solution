package com.yoon.demo.entity;

import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("stock")
@Value
public class StockRedisEntity {

    @Id
    String productItemId;

    Long stockCount;
}

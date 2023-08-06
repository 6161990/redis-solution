package com.yoon.demo.service;

import com.yoon.demo.StockCommand;
import com.yoon.demo.entity.StockRedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockRedisImpl implements StockService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(StockCommand stock) {
        redisTemplate.opsForSet().add("productItemId", new StockRedisEntity(stock.getProductItemId(), stock.getStock()));
    }

    @Override
    public void decrease(StockCommand stock) {
        redisTemplate.execute(new SessionCallback<>() {
              @Override
              public Object execute(RedisOperations operations) throws DataAccessException {
                  operations.watch(stock.getProductItemId());
                  operations.multi();

                  String stockCount = String.valueOf(redisTemplate.opsForValue().get(stock.getProductItemId()));
                  if (Objects.isNull(stockCount) || Long.parseLong(stockCount) <= 0) {
                      operations.unwatch();
                      throw new IllegalStateException(stock.getProductItemId() + " 재고 소진 > 체크 시 " + stockCount);
                  }

                  Long decreasedStockCount = redisTemplate.opsForValue().decrement("stock");
                  if (Objects.isNull(decreasedStockCount) || decreasedStockCount <= 0) {
                      operations.discard();
                      throw new IllegalStateException(stock.getProductItemId() + " 재고 소진 > 감소 시 " + stockCount);
                  }

                  operations.exec();
                  return null;
              }
          }

        );
    }

}

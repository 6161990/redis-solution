package com.yoon.demo.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockRedisRepository extends CrudRepository<StockRedisEntity, String> {
}

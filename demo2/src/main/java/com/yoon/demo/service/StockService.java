package com.yoon.demo.service;

import com.yoon.demo.StockCommand;
import com.yoon.demo.entity.StockRedisEntity;

interface StockService {

    void add(StockCommand stock);
    void decrease(StockCommand stock);
}

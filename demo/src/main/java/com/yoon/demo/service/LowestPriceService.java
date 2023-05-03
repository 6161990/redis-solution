package com.yoon.demo.service;

import com.yoon.demo.vo.Product;

import java.util.Set;

public interface LowestPriceService {
    Set getZsetValue(String key); /** 상품과 가격을 함께 return */

    int setNewProduct(Product product); /** 해당 상품의 가격이 몇 위인지 */
}

package com.yoon.demo.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {

    private final String prodGrpId; // FPG0001
    private final String productId; // d1fc1031-da1c-40da-9cd1-e9fef3f2a336
    private final int price; // 25000 (won)

}
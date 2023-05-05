package com.yoon.demo.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ProdGrp {

    private final String prodGrpId; // FPG0001 --> 하기스 3단계 기저귀
    private final List<Product> productList; // [{d1fc1031-da1c-40da-9cd1-e9fef3f2a336, 25000}, {}...]
}
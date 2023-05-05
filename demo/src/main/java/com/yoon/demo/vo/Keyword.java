package com.yoon.demo.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Keyword {

    private final String keyword; // 유아용품 - 하기스귀저기(FPG0001), A사 딸랑이(FPG0002)

    private final List<ProdGrp> productGrpList; // [{"FPG0001",[{d1fc1031-da1c-40da-9cd1-e9fef3f2a336. 25000}, {}...]}, "FPG0002"}

}
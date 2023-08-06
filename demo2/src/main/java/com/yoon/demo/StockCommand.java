package com.yoon.demo;

import lombok.Data;

@Data
public class StockCommand {
    String productItemId;
    Long stock;
}

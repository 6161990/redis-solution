package com.yoon.demo.controller;

import com.yoon.demo.service.LowestPriceService;
import com.yoon.demo.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceService mlps;

    @GetMapping("/product")
    public Set getZsetValue (String key){
        return mlps.getZsetValue(key);
    }

    @PostMapping("/product")
    public int setNewProduct(@RequestBody Product product){
        return mlps.setNewProduct(product);
    }
}

package com.yoon.demo.controller;

import com.yoon.demo.service.LowestPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LowestPriceController {

    private final LowestPriceService mlps;

    @GetMapping("/product")
    public Set GetZsetValue (String key){
        return mlps.getZsetValue(key);
    }
}

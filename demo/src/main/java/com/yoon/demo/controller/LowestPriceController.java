package com.yoon.demo.controller;

import com.yoon.demo.service.LowestPriceService;
import com.yoon.demo.vo.Keyword;
import com.yoon.demo.vo.ProdGrp;
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

    @PutMapping("/productGrp")
    public int setNewProduct(@RequestBody ProdGrp productGroup){
        return mlps.setNewProductGrp(productGroup);
    }

    @PutMapping("/productGrpToKeyword")
    public int setNewProduct(String keyword, String prodGrpId, double score){
        return mlps.setNewProductGrpToKeyword(keyword, prodGrpId, score);
    }

    @GetMapping("/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword(String keyword){
        return mlps.getLowestPriceProductByKeyword(keyword);
    }
}

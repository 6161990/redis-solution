package com.yoon.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.demo.vo.Keyword;
import com.yoon.demo.vo.ProdGrp;
import com.yoon.demo.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Set getZsetValue(String key) {
        return redisTemplate.opsForZSet().rangeByScore(key, 0, 10);
    }

    @Override
    public int setNewProduct(Product product) {
        redisTemplate.opsForZSet().add(product.getProdGrpId(), product.getProductId(), product.getPrice()); /** key, value , score */
        return redisTemplate.opsForZSet().rank(product.getProdGrpId(), product.getProductId()).intValue();
    }

    @Override
    public int setNewProductGrp(ProdGrp newProductGrp) {
        List<Product> productList = newProductGrp.getProductList();
        productList.forEach(i->
                redisTemplate.opsForZSet().add(newProductGrp.getProdGrpId(), i.getProductId(), i.getPrice()));
        return redisTemplate.opsForZSet().zCard(newProductGrp.getProdGrpId()).intValue(); /** 해당 상품 그룹의 총 상품 갯수*/
    }

    @Override
    public int setNewProductGrpToKeyword(String keyword, String prodGrpId, double score) {
        redisTemplate.opsForZSet().add(keyword, prodGrpId, score);
        return redisTemplate.opsForZSet().rank(keyword, prodGrpId).intValue();
    }

    @Override
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        List<ProdGrp> prodGrpList = getProdGrpUsingKeyword(keyword);
        return new Keyword(keyword, prodGrpList);
    }

    public List<ProdGrp> getProdGrpUsingKeyword(String keyword){
        List<ProdGrp> prodGrpList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Set<Object> prodGrpIdList = redisTemplate.opsForZSet().reverseRange(keyword, 0, 9);
        prodGrpIdList.forEach(i-> {
            Set prodAndPriceList = redisTemplate.opsForZSet().rangeWithScores((String) i, 0, 9);
            prodAndPriceList.stream().forEach(d-> {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.convertValue(d, Map.class);
                // {"value": "PRODUCT_ID_32532"}, {"score": 13000}
                productList.add(new Product((String) i, (String) map.get("value"), (Integer) map.get("score")));
            });
            prodGrpList.add(new ProdGrp((String) i, productList));
        });

        return prodGrpList;
    }


}

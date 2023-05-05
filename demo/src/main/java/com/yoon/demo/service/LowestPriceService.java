package com.yoon.demo.service;

import com.yoon.demo.vo.Keyword;
import com.yoon.demo.vo.ProdGrp;
import com.yoon.demo.vo.Product;

import java.util.Set;

public interface LowestPriceService {
    Set getZsetValue(String key); /** 상품과 가격을 함께 return */

    int setNewProduct(Product product); /** 새로운 상품을 추가하고 해당 상품의 가격이 몇 위인지 */

    int setNewProductGrp(ProdGrp newProductGrp);  /** 새로운 상품 그룹을 추가하고 해당 상품 그룹의 총 상품 갯수 */

    int setNewProductGrpToKeyword(String keyword, String prodGrpId, double score);  /** 새로운 상품 그룹이 기존 키워드에 추가*/

    Keyword getLowestPriceProductByKeyword(String keyword); /** 키워드를 검색할 경우 가장 낮은 가격의 상품을 return*/


}

package com.scservice.productserver.service;



import com.scservice.productclient.ro.DecreaseStockRO;
import com.scservice.productclient.vo.ProductInfoVO;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:13 2018/7/30
 * @Description:
 */
public interface ProductService {

    /**
     * 通过商品id集合查询商品VO列表
     * @param productIdList
     * @return
     */
    List<ProductInfoVO> findList(List<Integer> productIdList);

    void decreaseStock(List<DecreaseStockRO> decreaseStockROList);
}

package com.scservice.productserver.repository;

import com.scservice.productserver.dataobject.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午2:46 2018/7/30
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    List<ProductInfo> findByProductIdIn(List<Integer> productIdList);
}

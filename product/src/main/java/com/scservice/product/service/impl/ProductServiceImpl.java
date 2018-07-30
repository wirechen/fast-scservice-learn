package com.scservice.product.service.impl;

import com.scservice.product.dataobject.vo.ProductInfoVO;
import com.scservice.product.repository.ProductInfoRepository;
import com.scservice.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:14 2018/7/30
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductInfoVO> findList(List<Integer> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream().map(productInfo -> {
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo, productInfoVO);
            return productInfoVO;
        }).collect(toList());
    }
}

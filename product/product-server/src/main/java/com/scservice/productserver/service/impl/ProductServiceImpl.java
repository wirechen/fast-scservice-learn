package com.scservice.productserver.service.impl;

import com.scservice.productclient.ro.DecreaseStockRO;
import com.scservice.productclient.vo.ProductInfoVO;
import com.scservice.productserver.dataobject.model.ProductInfo;
import com.scservice.productserver.repository.ProductInfoRepository;
import com.scservice.productserver.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

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

    @Override
    public void decreaseStock(List<DecreaseStockRO> decreaseStockROList) {
        for (DecreaseStockRO decreaseStockRO : decreaseStockROList) {
            int productId = decreaseStockRO.getProductId();
            int productQuantity = decreaseStockRO.getProductQuantity();
            ProductInfo productInfo = productInfoRepository.findById(productId).get();
            productInfo.setProductStock(productInfo.getProductStock() - productQuantity);
            productInfoRepository.save(productInfo);
        }
    }
}

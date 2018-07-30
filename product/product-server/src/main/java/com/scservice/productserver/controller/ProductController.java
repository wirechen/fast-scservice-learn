package com.scservice.productserver.controller;

import com.scservice.productclient.ro.ProductIdListRO;
import com.scservice.productclient.vo.ProductInfoVO;
import com.scservice.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:18 2018/7/30
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 获取商品列表(给订单服务用的)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoVO> listForOrder(@RequestBody ProductIdListRO productIdList) {
        return productService.findList(productIdList.getProductIds());
    }

}

package com.scservice.product.controller;

import com.scservice.product.dataobject.vo.ProductInfoVO;
import com.scservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/listForOrder")
    public List<ProductInfoVO> listForOrder(@RequestParam List<Integer> productIdList) {
        return productService.findList(productIdList);
    }
}

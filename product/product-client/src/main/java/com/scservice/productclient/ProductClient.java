package com.scservice.productclient;

import com.scservice.productclient.ro.ProductIdListRO;
import com.scservice.productclient.vo.ProductInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午10:42 2018/7/30
 * @Description:
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoVO> listForOrder(@RequestBody ProductIdListRO productIdList);
}

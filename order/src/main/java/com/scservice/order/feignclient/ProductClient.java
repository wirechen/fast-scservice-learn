package com.scservice.order.feignclient;

import com.scservice.order.dataobject.ro.ProductIdListRO;
import com.scservice.order.dataobject.vo.ProductInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:58 2018/7/30
 * @Description:
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoVO> listForOrder(@RequestBody ProductIdListRO productIdList);
}

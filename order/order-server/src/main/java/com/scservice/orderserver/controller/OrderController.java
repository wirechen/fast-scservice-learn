package com.scservice.orderserver.controller;

import com.scservice.orderserver.dataobject.ro.OrderCreateRO;
import com.scservice.orderserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:33 2018/7/30
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Map> createOrder(@RequestBody OrderCreateRO orderCreateRO) {
        List<String> orderIdList = orderService.create(orderCreateRO);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("orderId", orderIdList);
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }
}

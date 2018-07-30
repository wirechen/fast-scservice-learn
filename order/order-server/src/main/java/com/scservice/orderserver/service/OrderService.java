package com.scservice.orderserver.service;

import com.scservice.orderserver.dataobject.ro.OrderCreateRO;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:34 2018/7/30
 * @Description:
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderCreateRO
     * @return
     */
    List<String> create(OrderCreateRO orderCreateRO);
}

package com.scservice.order.repository;

import com.scservice.order.dataobject.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:25 2018/7/30
 * @Description:
 */
public interface OrderInfoRepository extends JpaRepository<OrderInfo, String> {

}

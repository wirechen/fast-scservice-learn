package com.scservice.order.dataobject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: WireChen
 * @Date: Created in 下午2:43 2018/7/30
 * @Description: order表映射对象
 */
@Data
@Entity
public class OrderInfo {

    @Id
    private String orderId;

    /* 用户Id */
    private Integer userId;

    /* 订单总价 */
    private BigDecimal orderPrice;

    /* 商品Id */
    private Integer productId;

    /* 商品名称 */
    private String productName;

    /* 商品数量 */
    private Integer productQuantity;

}

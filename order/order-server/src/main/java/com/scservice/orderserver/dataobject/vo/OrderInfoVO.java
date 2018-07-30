package com.scservice.orderserver.dataobject.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:09 2018/7/30
 * @Description: order返回客户端对象，序列化方便后期做缓存
 */
@Data
public class OrderInfoVO implements Serializable{

    private static final long serialVersionUID = -4937332187748343885L;

    private String orderId;

    private Integer userId;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;
}

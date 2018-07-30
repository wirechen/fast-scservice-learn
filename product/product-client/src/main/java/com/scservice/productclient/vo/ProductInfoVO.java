package com.scservice.productclient.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:09 2018/7/30
 * @Description: product返回客户端对象，序列化方便后期做缓存
 */
@Data
public class ProductInfoVO implements Serializable{

    private static final long serialVersionUID = -5548839966839630114L;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;
}

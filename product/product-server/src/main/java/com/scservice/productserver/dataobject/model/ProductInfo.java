package com.scservice.productserver.dataobject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: WireChen
 * @Date: Created in 下午2:43 2018/7/30
 * @Description: product表映射对象
 */
@Data
@Entity
public class ProductInfo {

    @Id
    @GeneratedValue
    private Integer productId;

    /* 商品名称 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

}

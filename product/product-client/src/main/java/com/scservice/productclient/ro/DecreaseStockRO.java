package com.scservice.productclient.ro;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: WireChen
 * @Date: Created in 下午11:28 2018/8/7
 * @Description:
 */
@Data
@AllArgsConstructor
public class DecreaseStockRO {

    private Integer productId;

    private Integer productQuantity;
}

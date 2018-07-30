package com.scservice.order.dataobject.ro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:36 2018/7/30
 * @Description:
 */
@Data
public class OrderCreateRO {

    private Integer userId;

    private List<Cart> cartList;

}

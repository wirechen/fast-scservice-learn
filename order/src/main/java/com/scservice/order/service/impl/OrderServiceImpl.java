package com.scservice.order.service.impl;

import com.scservice.order.dataobject.model.OrderInfo;
import com.scservice.order.dataobject.ro.OrderCreateRO;
import com.scservice.order.dataobject.ro.ProductIdListRO;
import com.scservice.order.dataobject.vo.ProductInfoVO;
import com.scservice.order.feignclient.ProductClient;
import com.scservice.order.repository.OrderInfoRepository;
import com.scservice.order.service.OrderService;
import com.scservice.order.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:34 2018/7/30
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 1. 查询商品信息(调用商品服务)
     * 2. 订单入库
     */
    @Override
    public List<String> create(OrderCreateRO orderCreateRO) {

        int userId = orderCreateRO.getUserId();

        // 遍历购物车
        List<Integer> productIdList =
                orderCreateRO.getCartList().stream().map(cart -> cart.getProductId()).collect(toList());

        // 通过Feign调用商品服务查询接口
        ProductIdListRO productIdListRO = new ProductIdListRO();
        productIdListRO.setProductIds(productIdList);
        List<ProductInfoVO> productInfoVOList = productClient.listForOrder(productIdListRO);

        List<String> orderIds = new ArrayList<>();
        orderCreateRO.getCartList().stream().forEach(cart -> {
            productInfoVOList.stream().forEach(productInfoVO -> {
                if (cart.getProductId() == productInfoVO.getProductId()) {
                    // 订单总价 = 单价 * 数量
                    BigDecimal orderPrice = productInfoVO.getProductPrice().multiply(new BigDecimal(cart.getProductQuantity()));
                    OrderInfo orderInfo = new OrderInfo();
                    String orderId = KeyUtil.genUniqueKey();
                    orderInfo.setOrderId(orderId);
                    orderInfo.setUserId(userId);
                    orderInfo.setOrderPrice(orderPrice);
                    orderInfo.setProductId(productInfoVO.getProductId());
                    orderInfo.setProductName(productInfoVO.getProductName());
                    orderInfo.setProductQuantity(cart.getProductQuantity());
                    orderInfoRepository.save(orderInfo);
                    orderIds.add(orderId);
                }
            });
        });

        return orderIds;
    }
}

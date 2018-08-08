package com.scservice.orderserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scservice.orderserver.dataobject.model.OrderInfo;
import com.scservice.orderserver.dataobject.ro.OrderCreateRO;
import com.scservice.orderserver.repository.OrderInfoRepository;
import com.scservice.orderserver.service.OrderService;
import com.scservice.orderserver.utils.KeyUtil;
import com.scservice.productclient.ProductClient;
import com.scservice.productclient.ro.DecreaseStockRO;
import com.scservice.productclient.ro.ProductIdListRO;
import com.scservice.productclient.vo.ProductInfoVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 1. 查询商品信息(调用商品服务)
     * 2. 订单入库
     */
    @Override
    @Transactional
    public List<String> create(OrderCreateRO orderCreateRO) {

        int userId = orderCreateRO.getUserId();

        // 遍历购物车
        List<Integer> productIdList =
                orderCreateRO.getCartList().stream().map(cart -> cart.getProductId()).collect(toList());

        // 通过Feign调用商品服务查询接口
        ProductIdListRO productIdListRO = new ProductIdListRO();
        productIdListRO.setProductIds(productIdList);
        List<ProductInfoVO> productInfoVOList = productClient.listForOrder(productIdListRO);

        // 生成订单(订单入库)
        List<String> orderIds = new ArrayList<>();
        List<DecreaseStockRO> decreaseStockROList = new ArrayList<>();
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
                    // 添加减库存对象
                    DecreaseStockRO decreaseStockRO = new DecreaseStockRO(cart.getProductId(), cart.getProductQuantity());
                    decreaseStockROList.add(decreaseStockRO);
                    orderIds.add(orderId);
                }
            });
        });

        // 发送MQ消息给商品服务执行扣库存
        amqpTemplate.convertAndSend("decreaseStockQueue", JSONObject.toJSONString(decreaseStockROList));  //消息队列名称和商品服务的保持一致
        return orderIds;
    }
}

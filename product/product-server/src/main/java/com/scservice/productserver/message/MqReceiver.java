package com.scservice.productserver.message;

import com.alibaba.fastjson.JSON;
import com.scservice.productclient.ro.DecreaseStockRO;
import com.scservice.productserver.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author: WireChen
 * @Date: Created in 下午10:30 2018/8/5
 * @Description: MQ消费者
 */
@Component
@Slf4j
public class MqReceiver {

    @Autowired
    private ProductService productService;

    @RabbitListener(queuesToDeclare = @Queue("decreaseStockQueue"))
    public void getMessageFromMQ(String message) {
        List<DecreaseStockRO> decreaseStockROList = JSON.parseArray(message, DecreaseStockRO.class);
        productService.decreaseStock(decreaseStockROList);
        log.info("【消费扣库存消息队列】{}", message);
    }

}

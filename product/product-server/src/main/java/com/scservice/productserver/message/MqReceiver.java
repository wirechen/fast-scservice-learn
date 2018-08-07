package com.scservice.productserver.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @Author: WireChen
 * @Date: Created in 下午10:30 2018/8/5
 * @Description:
 */
@Component
@Slf4j
public class MqReceiver {

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void getMessageFromMQ(String message) {
        log.info("【消费消息队列】{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange("myOrder"), key = "computer", value = @Queue("computerQueue")))
    public void getMessageFromMQ2(String message) {
        log.info("【computer 消费消息队列】{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange("myOrder"), key = "fruit", value = @Queue("fruitQueue")))
    public void getMessageFromMQ3(String message) {
        log.info("【fruit 消费消息队列】{}", message);
    }
}

package com.scservice.productserver;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: WireChen
 * @Date: Created in 下午10:33 2018/8/5
 * @Description:
 */
@Component
public class MqSendTest extends ProductServerApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @Test
//    public void sendMQ() {
//        amqpTemplate.convertAndSend("myQueue", "hello rabbitMQ");
//        amqpTemplate.convertAndSend("myOrder","computer","hello rabbitMQ----computer");
//        amqpTemplate.convertAndSend("myOrder","fruit","hello rabbitMQ----fruit");
//    }

}

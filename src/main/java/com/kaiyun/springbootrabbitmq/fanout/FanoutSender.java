/**
 * Project Name:springboot-rabbitmq
 * File Name:FanoutSender.java
 * Package Name:com.kaiyun.springbootrabbitmq.fanout
 * Date:2018年7月24日下午6:35:29
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.fanout;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msgString="fanoutSender :hello i am hzb";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange","广播订阅是忽略Routing Key", msgString);
    }

}


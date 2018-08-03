/**
 * Project Name:springboot-rabbitmq
 * File Name:HelloSender2.java
 * Package Name:com.kaiyun.springbootrabbitmq.hello
 * Date:2018年7月24日下午6:24:52
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.hello;
import java.util.Date;

/**
 * ClassName:HelloSender2 <br/>
 * Function: 多生产者-多消费者. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:24:52 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender2 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

}


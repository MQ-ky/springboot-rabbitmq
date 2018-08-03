/**
 * Project Name:springboot-rabbitmq
 * File Name:TopicSender.java
 * Package Name:com.kaiyun.springbootrabbitmq.topic
 * Date:2018年7月24日下午6:32:10
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.topic;
/**
 * ClassName:TopicSender <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:32:10 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", msg1);
        
        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", msg2);
    }

}


/**
 * Project Name:springboot-rabbitmq
 * File Name:TopicMessageReceiver.java
 * Package Name:com.kaiyun.springbootrabbitmq.topic
 * Date:2018年7月24日下午6:33:01
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.topic;
/**
 * ClassName:TopicMessageReceiver <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:33:01 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessageReceiver2 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver2  : " +msg);
    }

}


/**
 * Project Name:springboot-rabbitmq
 * File Name:UserReceiver.java
 * Package Name:com.kaiyun.springbootrabbitmq.user
 * Date:2018年7月24日下午6:30:30
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.user;
/**
 * ClassName:UserReceiver <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:30:30 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "userQueue")
public class UserReceiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("user receive  : " + user.getName()+"/"+user.getPass());
    }

}


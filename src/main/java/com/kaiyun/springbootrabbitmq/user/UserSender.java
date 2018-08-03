/**
 * Project Name:springboot-rabbitmq
 * File Name:UserSender.java
 * Package Name:com.kaiyun.springbootrabbitmq.user
 * Date:2018年7月24日下午6:30:05
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.user;
/**
 * ClassName:UserSender <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:30:05 <br/>
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
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user=new User();
        user.setName("hzb");
        user.setPass("123456789");
        System.out.println("user send : " + user.getName()+"/"+user.getPass());
        this.rabbitTemplate.convertAndSend("userQueue", user);
    }

}


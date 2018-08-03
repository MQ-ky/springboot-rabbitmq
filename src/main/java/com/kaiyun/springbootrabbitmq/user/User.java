/**
 * Project Name:springboot-rabbitmq
 * File Name:User.java
 * Package Name:com.kaiyun.springbootrabbitmq.user
 * Date:2018年7月24日下午6:29:33
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.kaiyun.springbootrabbitmq.user;
/**
 * ClassName:User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午6:29:33 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.io.Serializable;

public class User implements Serializable{
        private String name;
        private String pass;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPass() {
            return pass;
        }
        public void setPass(String pass) {
            this.pass = pass;
        }

}


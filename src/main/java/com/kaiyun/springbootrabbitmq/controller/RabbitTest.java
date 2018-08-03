
package com.kaiyun.springbootrabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kaiyun.springbootrabbitmq.callback.CallBackSender;
import com.kaiyun.springbootrabbitmq.fanout.FanoutSender;
import com.kaiyun.springbootrabbitmq.hello.HelloSender1;
import com.kaiyun.springbootrabbitmq.hello.HelloSender2;
import com.kaiyun.springbootrabbitmq.topic.TopicSender;
import com.kaiyun.springbootrabbitmq.user.UserSender;

/** 参考链接 https://www.cnblogs.com/boshen-hzb/p/6841982.html */
@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private UserSender userSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private CallBackSender callBackSender;
    
    /**
     *	一、单生产者-单消费者
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello() {
        helloSender1.send();
    }
    
    /**
     * 	二、单生产者-多消费者
     * 	生产者发送的10条消息，分别被两个消费者均匀接收了
     */
    @RequestMapping(value = "/oneToMany", method = RequestMethod.GET)
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }
        
    }
    
    /**
     * 	三、多生产者-多消费者
     * 	和一对多一样，接收端仍然会均匀接收到消息
     */
    @RequestMapping(value = "/manyToMany", method = RequestMethod.GET)
    public void manyToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg1:"+i);
            helloSender2.send("hellomsg2:"+i);
        }
        
    }
    
    /**
     * 	四、实体类传输测试
     *  springboot完美的支持对象的发送和接收，不需要格外的配置
     */
    @RequestMapping(value = "/userTest", method = RequestMethod.GET)
    public void userTest() {
           userSender.send();
    }
    
    /**
     * 	五、topic exchange类型rabbitmq测试
     * 
     * topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列
     * 
     * 	首先对topic规则配置，这里使用两个队列来测试（也就是在Application类中创建和绑定的
     * 	topic.message和topic.messages两个队列），其中topic.message的bindting_key为
     * 	“topic.message”，topic.messages的binding_key为“topic.#”；
     * 
     * 	由结果可知：
     * 	sender1发送的消息,routing_key是“topic.message”，所以exchange里面的绑定的
     * 	binding_key是“topic.message”、topic.＃都符合路由规则;所以sender1发送的消息，
     * 	两个队列都能接收到；
     * 	sender2发送的消息，routing_key是“topic.messages”，所以exchange里面的绑定的
     * 	binding_key只有topic.＃都符合路由规则;所以sender2发送的消息只有队列topic.messages能收到。
     */
    @RequestMapping(value = "/topicTest", method = RequestMethod.GET)
    public void topicTest() {
           topicSender.send();
    }
    
    /**
     * 	六、fanout exchange类型rabbitmq测试
     * 
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
     * 
     * 	这里使用三个队列来测试（也就是在Application类中创建和绑定的fanout.A、fanout.B、fanout.C）这三个队列都和
     * 	Application中创建的fanoutExchange转发器绑定。
     * 
     * 	由结果可知：就算fanoutSender发送消息的时候，指定了routing_key为"abcd.ee"，但是所有接收者都接受到了消息
     */
    @RequestMapping(value = "/fanoutTest", method = RequestMethod.GET)
    public void fanoutTest() {
           fanoutSender.send();
    }
    
    
    /**
     *	七、带callback的消息发送
     *
     *	增加回调处理，这里不再使用application.properties默认配置的方式，会在程序中显示的使用文件中的配置信息。
     *	该示例中没有新建队列和exchange，用的是第5节中的topic.messages队列和exchange转发器。消费者也是第5节中
     *	的topicMessagesReceiver
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void callbak() {
        callBackSender.send();
    }

}

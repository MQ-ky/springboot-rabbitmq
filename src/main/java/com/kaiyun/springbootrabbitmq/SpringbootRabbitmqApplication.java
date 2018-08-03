package com.kaiyun.springbootrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRabbitmqApplication {
	
	final static String queueName = "hello";

    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue");
    }
    
    @Bean
    public Queue userQueue() {
        return new Queue("userQueue");
    }
    
    /**
     *	 交换机路由的几种类型: 
     *	1、Direct Exchange:直接匹配,通过Exchange名称+RountingKey来发送与接收消息. 
     *
     *	2、Fanout Exchange:广播订阅,向所有的消费者发布消息,但是只有消费者将队列绑定到
     *	该路由器才能收到消息,忽略Routing Key. 
     *
     *	3、Topic Exchange：主题匹配订阅,这里的主题指的是RoutingKey,RoutingKey可以
     *	采用通配符,如:*或#，RoutingKey命名采用.来分隔多个词,只有消息这将队列绑定到该路
     *	由器且指定RoutingKey符合匹配规则时才能收到消息; 
     *
     *	4、Headers Exchange:消息头订阅,消息发布前,为消息定义一个或多个键值对的消息头
     *	,然后消费者接收消息同时需要定义类似的键值对请求头:(如:x-mactch=all或者x_match=any)，
     *	只有请求头与消息头匹配,才能接收消息,忽略RoutingKey. 
     *
     *	5、默认的exchange:如果用空字符串去声明一个exchange，那么系统就会使用”amq.direct”这个exchange，
     *	我们创建一个queue时,默认的都会有一个和新建queue同名的routingKey绑定到这个默认的exchange上去
     *
     *	如果有两个接收程序都是用了同一个的queue和相同的routingKey去绑定direct exchange的话，分发的行为是负载均衡的，
     *	也就是说第一个是程序1收到，第二个是程序2收到，以此类推。 
     *	如果有两个接收程序用了各自的queue，但使用相同的routingKey去绑定direct exchange的话，分发的行为是复制的，
     *	也就是说每个程序都会收到这个消息的副本。行为相当于fanout类型的exchange。
     */
    //===============以下是验证topic Exchange（主题匹配订阅）的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }
    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
    //===============以下是验证topic Exchange（主题匹配订阅）的队列==========
    
    
    //===============以下是验证Fanout Exchange（广播订阅）的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }
    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }
    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以上是验证Fanout Exchange的队列==========
    

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 	将队列topic.message与topicExchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
    }

    /**
     * 	将队列topic.messages与topicExchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
    }
    
    
    /**
     * 	将队列AMessage、BMessage、CMessage与fanoutExchange绑定. <br/>
     */
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqApplication.class, args);
	}
}

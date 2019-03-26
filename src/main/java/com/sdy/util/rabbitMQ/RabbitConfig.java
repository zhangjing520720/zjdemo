package com.sdy.util.rabbitMQ;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * 消息确认的config
 * @author Rex
 */
//@Component
public class RabbitConfig {

	@Value("${spring.rabbitmq.addresses}")
    private String addresses;
    
    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;
    
    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }
    
    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
    
    
    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue");
    }
   
    
    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
    
    @Bean
    public Queue queueAck() {
        return new Queue("topic.ack");
    }
  //===============以上是验证topic Exchange的队列==========
    
    
    //===============以下是验证Fanout Exchange的队列==========
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
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(queueMessage()).to(exchange() ).with("topic.message");
    }
    
    /**
     * 将队列topicack与exchange绑定
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeAck() {
        return BindingBuilder.bind(queueAck()).to(exchange() ).with("topicack");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages() {
        return BindingBuilder.bind(queueMessages()).to(exchange() ).with("topic.#");
    }
    
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(AMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(BMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(CMessage()).to(fanoutExchange());
    }
    
    @Bean  
    public SimpleMessageListenerContainer messageContainer() {  
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());  
        container.setQueues(queueAck());  
        container.setExposeListenerChannel(true);  
        container.setMaxConcurrentConsumers(1);  
        container.setConcurrentConsumers(1);  
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);  
        container.setMessageListener(new TopicMessageAckReceiver() {  });  
        return container;  
    }  
  
    /**
     * 以下为延迟队列测试
     * 
     */
    //exchange name  
    public static final String DEFAULT_EXCHANGE = "KSHOP";  
    //DLX QUEUE  
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "kshop.dead.letter.queue";  
    //DLX repeat QUEUE 死信转发队列  
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "kshop.repeat.trade.queue";  
    //Hello 测试消息队列名称  
    public static final String HELLO_QUEUE_NAME = "DelayQueue";  
    /**
     * 信道配置  
     * @return
     */
    @Bean  
    public DirectExchange defaultExchange() {  
        return new DirectExchange("KSHOP");  
    } 
    /**
     * 业务队列-实际消息
     * @return
     */
    @Bean  
    public Queue delayQueue() {  
        Queue queue = new Queue("DelayQueue");  
        return queue;   
    }  
  
    @Bean  
    public Binding binding() {  
        return BindingBuilder.bind(delayQueue()).to(defaultExchange()).with("DelayQueue");  
    }  
    /**
     * 缓存队列
     * @return
     */
    @Bean  
    public Queue repeatTradeQueue() {  
        Queue queue = new Queue("kshop.repeat.trade.queue",true,false,false);  
        return queue;   
    }  
      
    @Bean  
    public Binding  drepeatTradeBinding() {  
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with("kshop.repeat.trade.queue");  
    }  
    /**
     * 死信队列
     * @return
     */
    @Bean  
    public Queue deadLetterQueue() {  
        Map<String, Object> arguments = new HashMap<>();  
        arguments.put("x-dead-letter-exchange", "KSHOP");  
        arguments.put("x-dead-letter-routing-key", "kshop.repeat.trade.queue");  //死信转发到缓冲队列，缓冲队列转发到业务队列，好处是扩展性更强，可以有多个业务队列，但是只设置一个死信队列
        Queue queue = new Queue("kshop.dead.letter.queue",true,false,false,arguments);  
        System.out.println("arguments :" + queue.getArguments());  
        return queue;   
    }  
  
    @Bean  
    public Binding  deadLetterBinding() {  
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with("kshop.dead.letter.queue");  
    }  
    
}

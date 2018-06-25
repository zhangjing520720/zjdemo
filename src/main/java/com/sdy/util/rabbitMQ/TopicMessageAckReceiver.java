package com.sdy.util.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.sdy.model.User;

@Component
public class TopicMessageAckReceiver implements ChannelAwareMessageListener{

	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {  
        try {  
        	System.out.println("消息传送次数："+message.getMessageProperties().getDeliveryTag());
        	// deliveryTag是消息传送的次数，我这里是为了让消息队列的第一个消息到达的时候抛出异常，处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列  
            if (message.getMessageProperties().getDeliveryTag() == 1 
                    || message.getMessageProperties().getDeliveryTag() == 2) { 
                throw new Exception(); 
            } 
            System.out.println("消费端接收到消息:" + message.getMessageProperties() + ":" + new String(message.getBody()));  
            System.out.println("topic:"+message.getMessageProperties().getReceivedRoutingKey());  
//            throw new Exception();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(message.getMessageProperties().getRedelivered());
            if (message.getMessageProperties().getRedelivered()) {  
                System.out.println("消息已重复处理失败,拒绝再次接收...");  
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息  
            } else {  
                System.out.println("消息即将再次返回队列处理...");  
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列  
            }  
        }  
    }  
}

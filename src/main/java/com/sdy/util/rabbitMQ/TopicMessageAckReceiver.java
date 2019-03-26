package com.sdy.util.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.sdy.model.User;

//@Component
public class TopicMessageAckReceiver implements ChannelAwareMessageListener{

	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {  
        try {  
        	if (message.getMessageProperties().getRedelivered()) {
            	System.out.println("进入第二次消费");
            	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息  
            } else{
            	System.out.println("进入第一次消费");
            	throw new Exception();
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息  
             
        } catch (Exception e) {  
            //e.printStackTrace();  
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列   
        }  
    }  
}

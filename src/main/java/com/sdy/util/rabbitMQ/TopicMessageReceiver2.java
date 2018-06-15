package com.sdy.util.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sdy.model.User;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessageReceiver2 {

	 @RabbitHandler
     public void process(String msg) {
         System.out.println("topicMessageReceiver2 : " +msg);
     }

}

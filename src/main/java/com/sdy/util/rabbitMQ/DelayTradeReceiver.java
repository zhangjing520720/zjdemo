package com.sdy.util.rabbitMQ;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.sdy.model.User;

//@Component
@RabbitListener(queues = "kshop.repeat.trade.queue")
public class DelayTradeReceiver {

    @Autowired
    private DelaySender delaySender;
    
    @RabbitHandler
    public void process(String content) {
    	System.out.println("DelayTradeReceiver.process:"+new Date().getTime());
    	DLXMessage message = JSON.parseObject(content, DLXMessage.class);  
    	delaySender.send(message.getQueueName(), message.getContent());  
    }
}

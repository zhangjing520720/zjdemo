package com.sdy.util.rabbitMQ;


import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;


//@Component
public class DelaySender {

	@Autowired
    private AmqpTemplate rabbitTemplate;

	
    public void send(String queueName, String msg) {
    	try{
    		this.rabbitTemplate.convertAndSend("KSHOP",queueName, msg);  
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }  
	
	public void send(String queueName, String msg,long times) {
		 System.out.println("DelaySender.send:"+new Date().getTime());
		 DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);  
	        MessagePostProcessor processor = new MessagePostProcessor(){  
	            @Override  
	            public Message postProcessMessage(Message message) throws AmqpException {  
	                message.getMessageProperties().setExpiration(times + "");  
	                return message;  
	            }  
	        };  
	        dlxMessage.setExchange("KSHOP");  
	        rabbitTemplate.convertAndSend("KSHOP","kshop.dead.letter.queue", JSON.toJSONString(dlxMessage), processor);  
    }
	
	

}

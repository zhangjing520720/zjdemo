package com.sdy.util.rabbitMQ;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HelloSender1 {

	@Autowired
    private AmqpTemplate rabbitTemplate;

	public void send() {
    	String sendMsg = "hello1 " + System.currentTimeMillis();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }
	
	public void sendMany(String msg) {
        String sendMsg = msg + System.currentTimeMillis();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

}

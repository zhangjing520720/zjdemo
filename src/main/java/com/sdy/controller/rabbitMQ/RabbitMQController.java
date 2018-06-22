package com.sdy.controller.rabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdy.controller.base.BaseController;
import com.sdy.util.rabbitMQ.CallBackSender;
import com.sdy.util.rabbitMQ.DelaySender;
import com.sdy.util.rabbitMQ.FanoutSender;
import com.sdy.util.rabbitMQ.HelloSender1;
import com.sdy.util.rabbitMQ.TopicSender;

/**
 * RabbiitMQ测试
 * 通过docker安装的RabbitMQ
 * 测试前需先通过doker启动RabbitMQ容器
 * @author Rex
 */
@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController extends BaseController{
	
	
	
	@Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private CallBackSender callBackSender;
    
    @Autowired
    private DelaySender delaySender;
    
    /**
     * 单生产-单消费
     */
    @RequestMapping(value= "/one2one",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
    public String  rabbitmq() {
        helloSender1.send();
        return "{\"msg\":\"success\"}";
    }
	
    /**
     * 单生产者-多消费者
     */
    @RequestMapping(value= "/one2Many",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.sendMany("hellomsg"+i+": ");
        }
        return "{\"msg\":\"success\"}";
    }
    
    /**
     * 多生产者-多消费者
     * @throws InterruptedException 
     */
    @RequestMapping(value= "/many2Many",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String manyToMany() throws InterruptedException {
        for(int i=0;i<10;i++){
            helloSender1.sendMany("hellomsg"+i+": ");
            helloSender2.sendMany("hellomsg"+i+": ");
        }
        return "{\"msg\":\"success\"}";
    }
    
    
    /**
     * topic exchange类型rabbitmq测试
     */
    @RequestMapping(value= "/topicTest",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String topicTest() {
           topicSender.send();
           return "{\"msg\":\"success\"}";
    }
    /**
     * fanout exchange类型rabbitmq测试
     */
    @RequestMapping(value= "/fanoutTest",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String fanoutTest() {
           fanoutSender.send();
           return "{\"msg\":\"success\"}";
    }
    /**
     * rabbitmq 消息确认
     */
    @RequestMapping(value= "/callback",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String callbak() {
        callBackSender.send();
        return "{\"msg\":\"success\"}";
    }
    
    /**
     * rabbitmq 延时队列
     */
    @RequestMapping(value= "/delay",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String delay() {
    	delaySender.send("DelayQueue","测试延迟发送消息",10000);
        return "{\"msg\":\"success\"}";
    }
    
    /**
     * rabbitmq 延时队列 普通模式测试
     */
    @RequestMapping(value= "/delay1",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String delay1() {
    	delaySender.send("DelayQueue","测试延迟发送消息");
        return "{\"msg\":\"success\"}";
    }
}

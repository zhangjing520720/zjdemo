package com.sdy.controller.rabbitMQ;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.model.Page;
import com.sdy.model.User;
import com.sdy.service.user.UserService;
import com.sdy.util.PageData;
import com.sdy.util.rabbitMQ.HelloSender1;
import com.sdy.util.redis.RedisUtil;

/**
 * RabbiitMQ测试
 * 通过docker安装的RabbitMQ
 * @author Rex
 */
@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController extends BaseController{
	
	
	
	@Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    
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
	
}

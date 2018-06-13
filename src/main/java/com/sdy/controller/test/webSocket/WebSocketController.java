package com.sdy.controller.test.webSocket;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.java_websocket.WebSocketImpl;
import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.model.MessagePackModel;
import com.sdy.model.Page;
import com.sdy.model.User;
import com.sdy.service.user.UserService;
import com.sdy.util.IpUtil;
import com.sdy.util.PageData;

import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
@RequestMapping("/wb")
public class WebSocketController extends BaseController{
	/**
	 * 使用方法：启动程序后先调用 start方法 
	 * 然后IP1访问home IP2也访问home
	 * 然后IP1调用pushMessage2  IP2调用pushMessage1
	 * 主页面是webSocketHome.jsp
	 */
	
	private String ip = "192.168.2.138";//服务器IP
	private String ip1 = "192.168.2.181";
	private String ip2 = "192.168.2.138";
	
	
	@RequestMapping("/push/{message}")
	@ResponseBody
	public String push(HttpServletRequest request,@PathVariable String message){
		String ip = IpUtil.getRemoteIp(request).equals("0:0:0:0:0:0:0:1")?ip1:IpUtil.getRemoteIp(request);
		if(ip.equals(ip1)){
			JSONObject result = new JSONObject();
			result.put("type", message);
			result.put("dfip", ip2);
			//给ip1发消息
			OnlineChatServerPool.sendMessageToUser(OnlineChatServerPool.getWebSocketByUser(ip2),result.toJSONString());	
		}else{
			JSONObject result = new JSONObject();
			result.put("type", message);
			result.put("dfip", ip1);
			//给ip1发消息
			OnlineChatServerPool.sendMessageToUser(OnlineChatServerPool.getWebSocketByUser(ip1),result.toJSONString());	
		}
		
		return message;
	}
	
	@RequestMapping("/push1/{message}")
	@ResponseBody
	public String pushMessage1(HttpServletRequest request,@PathVariable String message){
//		String ip = getRemoteIp(request);
//		if(ip.equals("0:0:0:0:0:0:0:1")){
//			ip="192.168.2.138";
//		}
//		ip="192.168.2.185";
		JSONObject result = new JSONObject();
		result.put("type", message);
		result.put("dfip", IpUtil.getRemoteIp(request).equals("0:0:0:0:0:0:0:1")?ip1:IpUtil.getRemoteIp(request));
		//给ip1发消息
		OnlineChatServerPool.sendMessageToUser(OnlineChatServerPool.getWebSocketByUser(ip1),result.toJSONString());	
		return message;
	}
	@RequestMapping("/push2/{message}")
	@ResponseBody
	public String pushMessage2(HttpServletRequest request,@PathVariable String message){
//		String messsge ="发布消息成功";
//		String ip = getRemoteIp(request);
//		ip="192.168.2.138";
		JSONObject result = new JSONObject();
		result.put("type", message);
		result.put("dfip", IpUtil.getRemoteIp(request).equals("0:0:0:0:0:0:0:1")?ip2:IpUtil.getRemoteIp(request));
		//给ip2发消息
		OnlineChatServerPool.sendMessageToUser(OnlineChatServerPool.getWebSocketByUser(ip2),result.toJSONString());	
		return message;
	}
	@RequestMapping("/home")
	public ModelAndView home(Page page){
		ModelAndView mv = this.getModelAndView();
		try{
			mv.setViewName("information/test/webSocketHome");
			mv.addObject("ip", ip);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@ResponseBody
	@RequestMapping("/start")
	public String start(){
		WebSocketImpl.DEBUG = false;
		OnlineChatServer s;
		try {
			s = new OnlineChatServer(8887);
			s.start();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		return "SUCCESS";
	}
	

		
		
}

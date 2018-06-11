package com.sdy.controller.information.user;

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
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.DelayQueue;
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
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.controller.information.test.annotation.AuthLogin;
import com.sdy.domain.Page;
import com.sdy.domain.User;
import com.sdy.domain.MessagePackModel;
import com.sdy.service.user.UserService;
import com.sdy.util.PageData;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	/*
	 * 一个springboot整合jsp的实例
	 */
	private static String Commonkey ="BC-433bbd1216354d05873d0f5f666624da";
	private static String Subscribekey ="BS-ef43aa76d7e5424bbd542d4c633fcc55";
	
	 static AtomicLong atomic = new AtomicLong(1);

	
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 限流总请求数示例
	 */
	@RequestMapping("/xianliuTotal")
	@ResponseBody
	public String xianliuTotal(HttpServletRequest request){
		String message="success";
		
		
		
		try {
		    if(atomic.incrementAndGet() >1) {
		    	System.out.println(atomic.get());
		    	message="限流了";
		    }
		    
		} finally {
		    atomic.decrementAndGet();
		}
		return message;
	}
	
	/**
	 * 限流接口时间窗请求数示例
	 */
	@RequestMapping("/xianliuTime")
	@ResponseBody
	public String xianliuTime(HttpServletRequest request){
		String message="success";
		
		
		return message;
	}
	
	/**
	 *@JsonView示例
	 */
	@RequestMapping("/getUsers")
	@ResponseBody
	@JsonView(View.SummaryWithDetail.class) 
	public List<User> getUsers(){
		 List<User> listUsers = new ArrayList<User>();
		 User u = new User();
		 u.setId("id");
		 u.setName("name");
		 u.setSex("sex");
		 u.setBirthDay("birthDay");
		 listUsers.add(u);
	     return listUsers; 
	}
	
	@RequestMapping("/getList")
	public ModelAndView getList(Page page){
	
		ModelAndView mv = this.getModelAndView();
		List<PageData> list = new ArrayList<PageData>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			//list =(List<PageData>)userService.infoListPage(page);
			System.out.println(list);
			mv.setViewName("information/user/user_list");
			mv.addObject("userList", list);
			mv.addObject("pd", pd);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	public String getU(String s){
		
		System.out.println("进来了");
		return s;
	}
		
	
	public static void main(String[] args) {
		
		System.out.println(1>>4);
	}
}

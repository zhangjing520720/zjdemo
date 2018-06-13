package com.sdy.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.domain.Page;
import com.sdy.domain.User;
import com.sdy.service.user.UserService;
import com.sdy.util.PageData;


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

package com.sdy.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.model.Page;
import com.sdy.model.User;
import com.sdy.service.user.UserService;
import com.sdy.util.PageData;
import com.sdy.util.redis.RedisUtil;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	//限流
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
		    	message="限流了";
		    }
		} finally {
		    atomic.decrementAndGet();
		}
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
	
	/**
	 * redis获取数据示例
	 */
	@ResponseBody
	@RequestMapping(value = "/getUser",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String getUser(HttpServletRequest request,@RequestBody PageData pd){
		String message ="";
		
		
		Object obj = RedisUtil.getKey(pd.get("id")==null?null:pd.get("id").toString());		
		if(null == obj){
			User user = new User();
			user.setId(pd.get("id").toString());
			User u = userService.findOneById(user);
			message =json.toJSONString(u);
		}else{
			message=obj.toString();
		}
		
		return message;
	}
	
	@ResponseBody
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

}

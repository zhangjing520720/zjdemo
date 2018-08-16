package com.sdy.controller.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.sdy.controller.user.UserController;
import com.sdy.model.Page;
import com.sdy.model.PageData;
import com.sdy.model.User;

/**
 * 参数传递测试类
 * @author Rex
 */
public class ParameterTest {
	
	public void update(int a,String b,StringBuffer c,PageData d ,User e){
		a=3;
		b="update";
		c.append(" update");
		d.put("name", "zj");
		System.out.println("e3:"+e);
		e=new User();
		e.setName("user");
		System.out.println("e4:"+e);
	}
	
	public static void main(String[] args) {
		int a = 1;
		String b = "main";
		StringBuffer c = new StringBuffer("main");
		PageData d = new PageData();
		User e = new User();
		e.setId("1");
		System.out.println("e0:"+e);
		new ParameterTest().update(a,b,c,d,e);
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		System.out.println("c:"+c);
		System.out.println("d:"+d);
		System.out.println("e:"+e.getName());
		
		System.out.println("e1:"+e);
	}

}

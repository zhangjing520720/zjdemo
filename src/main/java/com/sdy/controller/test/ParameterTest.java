package com.sdy.controller.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sdy.controller.user.UserController;
import com.sdy.model.Page;
import com.sdy.model.User;

/**
 * 参数传递测试类
 * @author Rex
 */
public class ParameterTest {
	
	public void update(int a,String b,StringBuffer c){
		a=3;
		b="update";
		c.append(" update");
	}
	
	public static void main(String[] args) {
		int a = 1;
		String b = "main";
		StringBuffer c = new StringBuffer("main");
		
		
		new ParameterTest().update(a,b,c);
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		System.out.println("c:"+c);
	}

}

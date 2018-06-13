package com.sdy.controller.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.sdy.controller.user.UserController;
import com.sdy.model.Page;
import com.sdy.model.User;

public class ClassTest {
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		UserController uc = new UserController();
		Class c = uc.getClass();
		
		System.out.println(c.getName());
		Method invokeMethod = c.getMethod("getList", new Class[] { Page.class });
		Object[] arguments = new Object[] { new Page() };
		Object result = (Object) invokeMethod.invoke(c.newInstance(), arguments);
		System.out.println(result);
		
		
		
	}

}

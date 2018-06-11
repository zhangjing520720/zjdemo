package com.sdy.controller.information.test.classLoad;

public class SonTest extends FatherTest{
	
	static{
		
		System.out.println("子类静态代码块");
	}
	 public void show()
	 {
	        System. out.println("子类show()方法");
	 }
	 public void show(String s)
	 {
	        System. out.println(s);
	 }
	 
	 public SonTest()
	 {
	        System. out.println("子类构造函数初始化完成" );
	        show();
	        show("子类重载show()方法");
	 }

}

package com.sdy.controller.test.classLoad;

public class SunTest extends SonTest{
	
	
	public void show()
	 {
	        System. out.println("孙类show()方法");
	 }
	 public SunTest()
	 {
	        System. out.println("孙类构造函数初始化完成" );
	        show();
	 }

}

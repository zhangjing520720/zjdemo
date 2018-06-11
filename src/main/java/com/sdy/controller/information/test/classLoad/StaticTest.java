package com.sdy.controller.information.test.classLoad;

public class StaticTest {
	
	 static{
		 
		  System. out.println("静态代码块" );
	  }
	  public static String print()
	  {
		  	 System.out.println(hehe);
	         System.out.println("静态方法" );
	         return "" ;
	  }
	  private static String hehe ="静态变量";
	  private static String s=print();
	  
	  public static void main(String[] args) {
		new StaticTest();
	}
}

package com.sdy.controller.information.test.classLoad;

public class Test2{

	
	
	public static void main(String[] args) {
		
//		A son = new A();
		B b = new B();
//		new SonTest();System.out.println("------");
//		new FatherTest();
//		new SonTest();System.out.println("------");
//		new SunTest();
	}
}
class A{  
	
	  static{
			 System. out.println("A静态" );
      }
	
	  {
	         System. out.println("A代码块初始化" );
	  }
	
	  public A(){
	         System. out.println("A构造方法" );
	  }
}  
  
class B extends A{
	static{
		 System. out.println("b静态" );
	 }
	
	 {
	        System. out.println("b代码块初始化" );
	 }
	
	 public B(){
	        System. out.println("b构造方法" );
	 }
}
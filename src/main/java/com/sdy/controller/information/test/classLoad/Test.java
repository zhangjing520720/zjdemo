package com.sdy.controller.information.test.classLoad;


/**
 * 类加载顺序测试
 * @author zhangjing
 *
 */
public class Test{

	
	
	public static void main(String[] args) {
		int i=12;
		System.out.println(i+=i-=i*=i);
		System.out.println("开始执行main方法");
		Son son = new Son();
		System.out.println(son.getName());
//		new SonTest();System.out.println("------");
//		new FatherTest();
//		new SonTest();System.out.println("------");
//		new SunTest();
	}
}
/**
 * 父类 
 * @author zhangjing
 */
class Father{  
	  private String name="father";
	  public String getName(){
		
		  	 return name;
	  }
	  private static String s=print();
	  static{
		  	 System.out.println(s);
          	 System. out.println("父类静态代码块初始化" );
	  }
	  {
	         System. out.println("父类代码块初始化" );
	  }
	
	  public static String print()
	  {
	         System. out.println("父类静态方法" );
	         return "父类静态成员变量的初始化" ;
	  }
	  private static String hehe ="静态变量";
	  public Father()
	  {
	         System. out.println("父类无参构造函数初始化完成" );
	         show();
	  }
	  public void show()
	  {
	         System. out.println("父类show()方法" );
	  }
}  
 /**
  * 子类 
  * @author zhangjing
  */
class Son extends Father{
	
	   private String name="son";
	   public String getName(){
		
		   	  return name;
	   }
	   static{
	          System. out.println("子类静态代码块初始化" );
	   }
	   {
	          System. out.println("子类代码块初始化" );
	   }
	   private static  int i=1;
	   private String s="子类私有成员变量" ;
	   public void show()
	   {
	          System. out.println("子类show()方法：i=" +i);
	   }
	   public Son()
	   {
	          System. out.println("子类构造函数初始化完成" );
	          show();
	   }
}
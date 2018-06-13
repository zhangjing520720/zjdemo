package com.sdy.controller.test.thread;

public class Thread2 extends Thread{
	
	
	public void run(){
		
		System.out.println("我是线程2");
		
		System.out.println("我是线程4");
		System.out.println("我是线程6");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我是线程8");
	}

}

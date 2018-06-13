package com.sdy.controller.test.thread;

public class Thread3 {
	
	
public void run(){
		
		System.out.println("我不是线程1");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我不是线程2");
	}

}

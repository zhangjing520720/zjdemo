package com.sdy.controller.test.thread;

public class Thread1 extends Thread{
	
	
	public void run(){

		System.out.println("我是线程1");
		System.out.println("我是线程3");
		System.out.println("我是线程5");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我是线程7");
	}

}

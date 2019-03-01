package com.sdy.controller.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	
	public static class MyTask extends Thread{
		 
		@Override
		public void run() {
 
			System.out.println(System.currentTimeMillis()+":Thread ID:"
			+Thread.currentThread().getId());
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		MyTask task = new MyTask();
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i = 0;i<10;i++){
			es.execute(task);
		}
	}
}

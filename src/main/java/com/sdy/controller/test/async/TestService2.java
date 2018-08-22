package com.sdy.controller.test.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class TestService2 {
	
	//异步方法
    @Async
    public void executeAsyncTask(Integer i){
    	
         System.out.println("执行异步任务:"+i);
    }

    //同步方法
    public void executeAsyncTask2(Integer i){
         System.out.println("执行异步任务2:"+i);
    }

    
    //异步方法-带返回值
    @Async
    public Future<String>  executeAsyncTask3(){
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return new AsyncResult<String>("执行异步任务3");
    }
    
}

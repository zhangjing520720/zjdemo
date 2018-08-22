package com.sdy.controller.test.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 异步方法测试
 * @author Rex
 */
public class TestController {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
	    
	    TestService2 testService2 = context.getBean(TestService2.class);
	    //异步带返回值
	    Future<String> future = testService2.executeAsyncTask3();
	    
//	    //同步方法
//	    for(int i = 0; i<10; i++){
//	        testService2.executeAsyncTask2(i);
//	    }
//	    //异步方法
//	    for(int i = 0; i<10; i++){
//	        testService2.executeAsyncTask(i);
//	    }
	    
	    while (true) {
            if(future.isCancelled()){
            	System.out.println("事务线程取消");
                break;
            }
            if (future.isDone() ) {
            	System.out.println("线程执行完成");
            	System.out.println(future.get());
                break;
            }
            try {
            	System.out.println("等待线程结束");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	    context.close();

	 }

}

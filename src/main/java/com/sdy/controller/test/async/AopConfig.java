package com.sdy.controller.test.async;


import java.util.concurrent.Executor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;	
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
@Configuration
@ComponentScan({"com.sdy.controller.test.async"})
//开始异步支持
@EnableAsync
public class AopConfig implements AsyncConfigurer{

	@Override
    public Executor getAsyncExecutor() {
         //线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
         return null;
    }
}

package com.sdy.controller.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class TaskPoolConfig {
	
	 @Bean
     public Executor taskExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         executor.setCorePoolSize(2);
         executor.setMaxPoolSize(3);
         executor.setQueueCapacity(200);
         executor.setKeepAliveSeconds(60);
         executor.setThreadNamePrefix("taskExecutor-");
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
         executor.setWaitForTasksToCompleteOnShutdown(true);
         executor.setAwaitTerminationSeconds(60);

         return executor;
	 }

}

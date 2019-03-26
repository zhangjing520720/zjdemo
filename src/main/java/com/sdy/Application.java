package com.sdy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * zjdemo项目启动类
 * @author Rex
 */
@SpringBootApplication
@ServletComponentScan
@Configuration
public class Application {
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

	
	
	
}

package com.sdy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sdy.interceptor.ApiAuthInterceptor;

/**
 * API签名鉴权拦截器配置
 * 1、isAuth在auth.properties里面，配置是否验签
 * 2、不拦截某些请求 .excludePathPatterns.......
 * 3、拦截所有请求   .addPathPatterns("/**")
 * @author Rex
 */
@Configuration
@PropertySource(value={"classpath:apiAuth.properties"},ignoreResourceNotFound=true)
public class ApiAuthConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ApiAuthInterceptor apiAuth;
	@Value("${isAuth:false}")
    private boolean isAuth;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry){
    	if(isAuth){
    		System.out.println("要验签");
    		registry.addInterceptor(apiAuth)
        		.addPathPatterns("/**")
        		.excludePathPatterns("/user/xianliuTotal");
    	}else{
    		System.out.println("不验签");
    		registry.addInterceptor(apiAuth)
        		.excludePathPatterns("/**");
    	}
    }
    
    
    
    
}
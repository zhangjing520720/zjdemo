package com.sdy.controller.information.test.annotation;
import java.lang.annotation.*;
/**
 * 自定义注解
 * @author zhangjing
 * 用于验证jwt
 */
//@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthLogin {
	
	 String value() default ""; 

}

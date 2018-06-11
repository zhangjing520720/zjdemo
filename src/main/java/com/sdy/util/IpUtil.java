package com.sdy.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	
	/**
	    * 获取请求IP
	    * @param HttpServletRequest 
	    * @return String IP
	    */
		public static String getRemoteIp(HttpServletRequest request) {  
	        String ip = request.getHeader("x-forwarded-for");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        final String[] arr = ip.split(",");  
	        for (final String str : arr) {  
	            if (!"unknown".equalsIgnoreCase(str)) {  
	                ip = str;  
	                break;  
	            }  
	        }  
	        return ip;  
	    }  

}

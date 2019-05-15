package com.sdy.interceptor;

import com.alibaba.fastjson.JSON;
import com.sdy.filter.RequestWrapper;
import com.sdy.util.DateUtil;
import com.sdy.util.MD5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * API验签拦截器
 * @author Rex
 */
@Component
@PropertySource(value={"classpath:apiAuth.properties"},ignoreResourceNotFound=true)
public class ApiAuthInterceptor implements HandlerInterceptor {
	
	@Value("${key:PANDAPARKING}")
    private String KEY;
	@Value("${expiration:3600}")
    private long expiration;
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
    	
        String sign = httpServletRequest.getHeader("sign");
        String timestampStr = httpServletRequest.getHeader("timestamp");
        String noncestr = httpServletRequest.getHeader("noncestr");
        // 拦截之后应该返回公共结果, 这里没做处理
        httpServletResponse.setContentType("application/json; charset=utf-8");
		httpServletResponse.setCharacterEncoding("UTF-8");
	    httpServletResponse.setStatus(200);
	    
        if(sign !=null && !sign.equals("") && timestampStr !=null && !timestampStr.equals("")  && noncestr !=null && !noncestr.equals("") ){
        	try{
	        	if(Math.abs(DateUtil.getCurrentTimestemp()  - Long.valueOf(timestampStr)) > expiration ) {//当前时间与时间戳相减大于300秒
	        		PrintWriter writer = httpServletResponse.getWriter();
	        		writer.print("{\"code\":\"407\",\"msg\":\"签名失效，请重试！\"}");
	                return false;
	            }
	        	if(httpServletRequest.getMethod().equals("GET")){
		        	if(!sign.equals(getSign(getGETParams(httpServletRequest),KEY,Long.valueOf(timestampStr),noncestr))){
		        		PrintWriter writer = httpServletResponse.getWriter();
		        		writer.print("{\"code\":\"407\",\"msg\":\"GET签名错误！\"}");
		        		System.out.println("验签失败！");
		        		return false;
			        }
	        	}else{
        			RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);	
	        		System.out.println("原始参数："+requestWrapper.getBody());
	        		Map<String,Object> map = new HashMap<>();
	        		if(requestWrapper.getBody()!=null && !requestWrapper.getBody().equals("")){
	        			map=JSON.parseObject(requestWrapper.getBody(), Map.class);
	        		}
	        		System.out.println("平台取出到map后的参数："+JSON.toJSONString(map));
	        		if(!sign.equals(getSign2(map,KEY,Long.valueOf(timestampStr),noncestr))){
		        		PrintWriter writer = httpServletResponse.getWriter();
		        		writer.print("{\"code\":\"407\",\"msg\":\"签名错误！\"}");
		        		return false;
			        }
	        		
	        		
	        		
	        	}
	        	System.out.println("验签成功！");
	        	return true;
        	}catch (Exception e) {
        		e.printStackTrace();
        		PrintWriter writer = httpServletResponse.getWriter();
        		writer.print("{\"code\":\"407\",\"msg\":\"服务器验签报错！\"}");
                return false;
			}
        }else{
        	PrintWriter writer = httpServletResponse.getWriter();
        	writer.print("{\"code\":\"407\",\"msg\":\"非法请求，参数不齐！\"}");
            return false;
        }
        
		

    }
    /**
     * 获取GET请求的参数
     */
    public static Map<String,String> getGETParams(HttpServletRequest request)  {
 	    Map<String,String> params = new HashMap<String,String>();
 	    
 	    Map<String,String[]> requestParams = request.getParameterMap();
 	    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
 	        String name = (String) iter.next();
 	        String[] values = (String[]) requestParams.get(name);
 	        String valueStr = "";
 	        for (int i = 0; i < values.length; i++) {
 	            valueStr = (i == values.length - 1) ? valueStr + values[i]
 	                    : valueStr + values[i] + ",";
 	        }
 	        params.put(name, valueStr);
 	    }
 	    return params;
    }
    
    /**
 	 * GET请求获取签名
 	 */
 	public static String getSign(Map<String,String> map,String key,long timestamp,String noncestr){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!=null && !entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        return  getSign(sb.toString(),key,timestamp,noncestr);
    }
 	
 	/**
 	 * ios端POST请求获取签名
 	 */
 	public static String getSign2(Map<String,Object> map,String key,long timestamp,String noncestr){
 		ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=null && !entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue().toString() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        return  getSign(sb.toString(),key,timestamp,noncestr);
    }
 	
 	
 	/**
 	 * 其他端POST请求获取签名
 	 */
 	public static String getSign(String body,String key,long timestamp,String noncestr){
        body +=noncestr+timestamp+key;
        System.out.println("Md5前Str："+body);
        body = MD5.md5(body).toUpperCase();
        System.out.println("Md5后Str："+body);
        return body;
    }
    
 	
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

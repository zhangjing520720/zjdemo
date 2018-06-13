package com.sdy.controller.test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.java_websocket.WebSocketImpl;
import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.base.BaseController;
import com.sdy.domain.Page;
import com.sdy.domain.User;
import com.sdy.domain.MessagePackModel;
import com.sdy.service.user.UserService;
import com.sdy.util.PageData;

import ch.qos.logback.core.net.SyslogOutputStream;
public class LambdaTest extends BaseController{
	
		
	/**
	 * 测试朗母达相关用法
	 * @param args
	 * @throws Exception
	 * @throws IOException
	 */
		public static void main(String[] args) throws Exception,IOException{

			
			String s ="1,2,hello.world,4,5";
			
			List<String> list =new ArrayList<String>();
			list.add("1");
			list.add("2");
			list.add("2");
			
			for(String s1:list){
				System.out.println(s1);
			}	
			StringBuffer sb = new StringBuffer();
			Map<String, String> items = new HashMap<>();
			for(int i=0;i<10;i++){
				items.put(i+"", i+"");
			}
			items.forEach(
				(k,v)->{
					sb.append(k);
				}
			);
			System.out.println(sb);
			Integer[] array = {5, 6, -1, 4,11}; 
			//Arrays.sort(array); 
			Arrays.asList(array).stream().filter(s1->s1>2).forEach(System.out::println);
			List<Integer> testlist = new ArrayList<>();
			testlist.add(31);
			testlist.add(1);
			testlist.add(7);
			testlist.add(5);
			for(int i=0;i<100000;i++){
				testlist.add(i);
			}
			
			
			
//			Collections.sort(testlist);
//			testlist.stream().forEach(System.out::println);

//			//假如一个list机会中的元素要排序  
//			List<String> list1 = Arrays.asList("hello","tom","apple","bbc");  
//			List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));  
//            list.add(4);
//            list.stream().forEach(System.out::println);
//            //之前的排序我们可以这样写  
//            Collections.sort(list, new Comparator<String>(){  
//                @Override  
//                public int compare(String o1, String o2) {  
//                    return o1.compareTo(o2);  
//                }  
//            });  
//              
//            //使用Lambda表达式  
//            Collections.sort(list,(String s1,String s2)->{  
//                return s1.compareTo(s2);  
//            });  
              
 
			
			
		}
}

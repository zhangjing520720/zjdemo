package com.sdy.controller.information.test;

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

public class XuliehuaTest extends BaseController{
	
	
		/**
		 * 测试几种方式序列化耗时
		 * @param args
		 * @throws Exception
		 * @throws IOException
		 */
		public static void main(String[] args) throws Exception,IOException{
			PageData pd =new PageData();
			pd.put("id", 100233);
			pd.put("name", "test");
			List<PageData> list = new ArrayList<PageData>();
			for(int i=0;i<10000;i++){
				list.add(pd);
			}
			
			//JSON
			List<String> list1 = new ArrayList<String>();
			List<JSONObject> list2 = new ArrayList<JSONObject>();
			long begin = System.currentTimeMillis();
			for(PageData user:list){
				list1.add(json.toJSONString(user));
			}
			System.out.println("JSON万次序列化耗时："+(System.currentTimeMillis()-begin)+"毫秒");
			long begin1 = System.currentTimeMillis();
			for(String userStr:list1){
				list2.add(json.parseObject(userStr));
				
			}
			System.out.println("JSON万次反序列化耗时："+(System.currentTimeMillis()-begin1)+"毫秒");
			
			//MessagePack
			List<byte[]> list3 = new ArrayList<byte[]>();
			List list4 = new ArrayList();
			MessagePack mp = new MessagePack();
			long mBegin = System.currentTimeMillis();
			for(PageData user:list){
				list3.add(mp.write(user));
			}
			System.out.println("MessagePack万次序列化耗时："+(System.currentTimeMillis()-mBegin)+"毫秒");
			
			long mBegin1 = System.currentTimeMillis();
			for(byte[] b:list3){
				list4.add(mp.read(b));
			}
			System.out.println("MessagePack万次反序列化耗时："+(System.currentTimeMillis()-mBegin1)+"毫秒");
			
			//Lambda  MessagePack
			List<byte[]> list5 = new ArrayList<byte[]>();
			List list6 = new ArrayList();
			long lmBegin = System.currentTimeMillis();
			list.stream().forEach(v->{try {list5.add(mp.write(v));} catch (IOException e) {}});
			System.out.println("朗母达-MessagePack万次序列化耗时："+(System.currentTimeMillis()-lmBegin)+"毫秒");
			
			long lmBegin1 = System.currentTimeMillis();
			list5.stream().forEach(v->{try {list6.add(mp.read(v));} catch (IOException e) {}});
			System.out.println("朗母达-MessagePack万次反序列化耗时："+(System.currentTimeMillis()-lmBegin1)+"毫秒");

			

		}
}

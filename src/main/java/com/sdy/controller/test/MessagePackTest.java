package com.sdy.controller.test;

import java.io.IOException;
import java.util.HashMap;

import org.msgpack.MessagePack;
import com.sdy.controller.base.BaseController;
import com.sdy.model.MessagePackModel;


public class MessagePackTest extends BaseController{
	
	
		/**
		 * 测试MessagePack
		 * @param args
		 * @throws Exception
		 * @throws IOException
		 */
		public static void main(String[] args) throws Exception,IOException{
			
			MessagePackModel mpm= new MessagePackModel();
			mpm.setId("test");
			MessagePack mp = new MessagePack();
			//序列化
			byte[] b = mp.write(mpm);
			//反序列化成字符串
			String str = mp.read(b).toString();
			System.out.println(str);
			//反序列化成对象
			MessagePackModel pd1= mp.read(b,MessagePackModel.class);
			System.out.println(pd1.getId());
			
			


		}
}

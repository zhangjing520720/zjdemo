package com.sdy.controller.test.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
 
 
public class ClientSocket {
	
	
	public static void main(String []args)
	{
		try {
			Socket s=new Socket(InetAddress.getByName(null),6000);//"localhost" "127.0.0.1s"
			PrintWriter pwtoserver =  new PrintWriter(s.getOutputStream(),true);
			InputStream is=s.getInputStream();
			byte []buf=new byte[100];
			int len=is.read(buf);
			System.out.println(new String(buf,0,len));
			
			pwtoserver.println("Hello,this is zhangsan");
			Thread.sleep(5000);
			is.close();
			s.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
}
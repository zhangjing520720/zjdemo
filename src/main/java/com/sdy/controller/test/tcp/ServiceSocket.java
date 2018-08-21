package com.sdy.controller.test.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
 
 
public class ServiceSocket extends Thread{
 
	public Socket sock;
	public ServiceSocket(Socket sock)
	{
		this.sock=sock;
	}

	public void run()
	{
		try{
			//输出到客户端
			PrintWriter pwtoserver =  new PrintWriter(sock.getOutputStream(),true);;
			pwtoserver.println("Hello Welcome you");
			
			//输入
			InputStream is=sock.getInputStream();
			pwtoserver.println("Hello Welcome you2");
			byte []buf=new byte[100];
			int len=is.read(buf);
			System.out.println(new String(buf,0,len));
			is.close();
			sock.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
 
}
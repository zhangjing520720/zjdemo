package com.sdy.controller.test.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			OutputStream os=sock.getOutputStream();
			InputStream is=sock.getInputStream();
			os.write("Hello Welcome you".getBytes());
			byte []buf=new byte[100];
			int len=is.read(buf);
			System.out.println(new String(buf,0,len));
			os.close();
			is.close();
			sock.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
 
}
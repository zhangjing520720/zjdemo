package com.sdy.controller.test.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TCPTest {
	
	public static HashMap<String,Socket> socketMap = new HashMap<>();
	
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(6000);
			System.out.println("Socket服务端已启动;");
			while(true)
			{
				Socket s=ss.accept();
				new ServiceSocket(s).start();
				System.out.println("s.getInetAddress().toString():s.getPort()"+s.getInetAddress().toString()+":"+s.getPort());
				socketMap.put(s.getInetAddress().toString()+":"+s.getPort(), s);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}

}

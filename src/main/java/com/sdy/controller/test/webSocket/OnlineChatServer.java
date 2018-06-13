package com.sdy.controller.test.webSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;




/**
 * 在线管理
 * @author rex
 */
public class OnlineChatServer extends WebSocketServer{
	

	public OnlineChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public OnlineChatServer(InetSocketAddress address) {
		super(address);
	}

	/**
	 * 触发连接事件
	 */
	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		String address=conn.getRemoteSocketAddress().getAddress().getHostAddress();
		System.out.println("有新的客户端连接：" +address);
		
	}

	/**
	 * 触发关闭事件
	 */
	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
		String address=conn.getRemoteSocketAddress().getAddress().getHostAddress();
		System.out.println("客户端:"+address+"关闭连接");
		
	}

	/**
	 * 客户端发送消息到服务器时触发事件
	 */
	@Override
	public void onMessage(WebSocket conn, String shopId){
		String address=conn.getRemoteSocketAddress().getAddress().getHostAddress();
		System.out.println("客户端:"+address+"向我发送消息啦，消息内容："+shopId);
		//客户端向服务器发了消息才将该链接保存
		OnlineChatServerPool.addUser(address,conn);
		//处理逻辑
	}

	

	/**
	 * 触发异常事件
	 */
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		//ex.printStackTrace();
		if( conn != null ) {
			//some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	

}


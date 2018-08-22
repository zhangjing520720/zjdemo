package com.sdy.controller.test.netty;


import com.sdy.util.redis.RedisUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

/**
 * 服务端Handler 
 * @author Rex
 */
public class EchoServerHandler extends ChannelHandlerAdapter{
	
	long counter = 0;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("This is " +counter + " times receive client : [" + body + "]");
		body="服务端已收到;";
		//body += ";";
		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(echo);
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {	
		try{
		
			EchoServer.hashMap.put(ctx.toString(), ctx);
			ByteBuf echo = Unpooled.copiedBuffer("和服务端连接了".getBytes());
			ChannelHandlerContext ctx1= EchoServer.hashMap.get(ctx.toString());
			SocketChannel sc = (SocketChannel)ctx1.channel();
			
			System.out.println(sc.remoteAddress().getPort());
			
			//sc.writeAndFlush(echo);
			System.out.println("和服务端连接了");
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//    	EchoServer.hashMap.remove(ctx.toString());
    	RedisUtil.remove("A_TCP_"+ctx.toString());
    	System.out.println("客户端退出链接了");
    }
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
		System.out.println("发生异常，关闭链路");
	}


}

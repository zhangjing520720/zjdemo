package com.sdy.controller.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * Netty服务器端
 * @author Rex
 */
//public class ChildChannelHandler  extends ChannelInitializer<SocketChannel>{
//	
//	ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//	 
//	@Override
//	protected void initChannel(SocketChannel arg0) throws Exception {
//		
//		arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
//		arg0.pipeline().addLast(new StringDecoder());
//		arg0.pipeline().addLast(new StringEncoder());
//		arg0.pipeline().addLast(new EchoServerHandler());	
//	}
//
//}

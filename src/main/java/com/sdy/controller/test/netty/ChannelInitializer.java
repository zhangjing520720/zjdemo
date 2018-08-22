//package com.sdy.controller.test.netty;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
//public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel>{
//	ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//	@Override
//	protected void initChannel(SocketChannel sc) throws Exception {
//		
//		sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
//		sc.pipeline().addLast(new StringDecoder());
//		sc.pipeline().addLast(new StringEncoder());
//		sc.pipeline().addLast(new EchoClientHandler());
//	}
//
//}

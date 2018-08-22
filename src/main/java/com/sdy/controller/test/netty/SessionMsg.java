//package com.sdy.controller.test.netty;
//
//import io.netty.channel.socket.SocketChannel;
//
//public class SessionMsg{
//
//	
//	//Session的唯一标识
//    private String id;
//    //和Session相关的channel,通过它向客户端回送数据
//    private SocketChannel channel = null;
//    //上次通信时间
//    private long lastCommunicateTimeStamp = 0l;
//
//    //快速构建一个新的Session    
//    public static SessionMsg buildSession(SocketChannel channel) {
//    	SessionMsg session = new SessionMsg();
//        session.setChannel(channel);
//
//        //此处暂且使用netty生成的类似UUID的字符串,来标识一个session
//        session.setId(channel.id().asLongText());
//        session.setLastCommunicateTimeStamp(System.currentTimeMillis());
//        return session;
//    }
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public SocketChannel getChannel() {
//		return channel;
//	}
//
//	public void setChannel(SocketChannel channel) {
//		this.channel = channel;
//	}
//
//	public long getLastCommunicateTimeStamp() {
//		return lastCommunicateTimeStamp;
//	}
//
//	public void setLastCommunicateTimeStamp(long lastCommunicateTimeStamp) {
//		this.lastCommunicateTimeStamp = lastCommunicateTimeStamp;
//	}
//
//    
//    
//}

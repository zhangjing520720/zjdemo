package com.sdy.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 屏幕显示工具类
 * @author rex
 *
 */
public class LEDUtil {
	
	/**
	 * 获取显示LED时需要的HashMap
	 * @param serialChannel 串口 		默认传1
	 * @param str			显示的字符串	如：欢迎光临
	 * @param textLine 		显示行数		0x21第一行 0x22第二行 0x23第三行 0x24第4行 
	 * @param command 		命令			0x25固定值
	 * @param sq			扇区			0-199为固定扇区，200-220为临时显示扇区
	 * @param beginOne 		开始第一行显示 0x0C固定值
	 * @param inType 		进入模式		0x20立即显示 0x21从右往左 0x22从左往右
	 * @param waitType 		停留模式		0x20固定值
	 * @param tlDate		停留时间		0x21=1秒 0x22=2秒 以此类推
	 * @param outType		退出模式		0x20固定值
	 * @param length		文档长度		0x29固定值
	 * @param color			颜色			0x10红色 0x11绿色 0x12黄色 
	 */
	public static PageData getLEDViewHashMap(int serialChannel,String str,byte textLine, byte command,byte sq,byte beginOne,byte inType,byte waitType,byte tlDate,byte outType,byte length,byte color) throws UnsupportedEncodingException{
		PageData  serialPd = new PageData();
		serialPd.put("serialChannel", serialChannel);
		String dataStr = getLEDViewBase64(str,textLine,command,sq,beginOne,inType,waitType,tlDate,outType,length,color);
		serialPd.put("data", dataStr);
		serialPd.put("dataLen", dataStr.length());
		return serialPd;
	}
	
	/**
	 * 获取显示LED时需要的Base64
	 */
	public static String getLEDViewBase64(String str,byte textLine, byte command,byte sq,byte beginOne,byte inType,byte waitType,byte tlDate,byte outType,byte length,byte color) throws UnsupportedEncodingException{
			 return Base64.getEncoder().encodeToString(getLEDViewByteArr(str,textLine,command,sq,beginOne,inType,waitType,tlDate,outType,length,color));
	}
	
	/**
	 * 获取显示LED时需要的byte数组
	 */
	public static byte[] getLEDViewByteArr(String str,byte textLine, byte command,byte sq,byte beginOne,byte inType,byte waitType,byte tlDate,byte outType,byte length,byte color) throws UnsupportedEncodingException{
		if(str!=null && !str.equals("")){
			byte [] zByte = str.getBytes("GBK");		
			byte [] strByte = new byte[zByte.length+12];
			strByte[0]=0x02;
			strByte[1]=textLine;//显示行数    0x23   	0x21第一行 0x22第二行 0x23第三行 0x24第4行 
			strByte[2]=command;	//命令       0x25    固定值
			strByte[3]=sq;		//扇区       0x21    0-199为固定扇区，200-220为临时显示扇区
			strByte[4]=beginOne;//开始一行显示 0x0C  	固定值
			strByte[5]=inType;	//进入模式 	0x20    0x20立即显示 0x21从右往左 0x22从左往右
			strByte[6]=waitType;//停留模式 	0x20 	固定值
			strByte[7]=tlDate;	//停留时间 	0x21 	0x21=1秒 0x22=2秒 以此类推
			strByte[8]=outType;	//退出模式 	0x20 	固定值
			strByte[9]=length;	//文档长度 	0x29  	固定值
			strByte[10]=color;	//颜色 		0x10    0x10红色 0x11绿色 0x12黄色 
			int s =11;
			for(byte sss :zByte){
				strByte[(s)]=sss;
				s++;
			}
			strByte[s]=(byte)0x03;
			return strByte;
		}
		return null;
	}
	
	
	
	/**
	 * 获取清除LED时需要的HashMap
	 */
	public static PageData getLEDClearHashMap(int serialChannel,byte textLine) throws UnsupportedEncodingException{
		PageData  serialPd = new PageData();
		serialPd.put("serialChannel", serialChannel);
		String dataStr = getLEDClearBase64(textLine);
		serialPd.put("data", dataStr);
		serialPd.put("dataLen", dataStr.length());
		return serialPd;
	}
	
	/**
	 * 获取清除LED时需要的Base64
	 */
	public static String getLEDClearBase64(byte textLine) {
			 return Base64.getEncoder().encodeToString(getLEDClearByteArr(textLine));
	}
	/**
	 * 获取清除LED时需要的byte数组
	 * @param textLine 0表示所有 1为第一行 2为第二行 以此类推
	 * @return
	 */
	public static byte[] getLEDClearByteArr(byte textLine){
		return new byte[]{0x02,textLine,0x28,0x20,0x03};
	}
	
	/**
	 * 获取LED上显示时间需要的HashMap
	 */
	public static PageData getLEDTimeHashMap(int serialChannel){
		PageData  serialPd = new PageData();
		serialPd.put("serialChannel", serialChannel);
		String dataStr = getLEDTimeBase64();
		serialPd.put("data", dataStr);
		serialPd.put("dataLen", dataStr.length());
		return serialPd;
	}
	
	/**
	 * 获取LED上显示时间的byte数组
	 */
	public static String getLEDTimeBase64() {
		 byte [] time = new byte[]{0x02,0x21,0x25,0x21,0x0C,0x22,0x20,0x25,0x20,0x3A,0x10,0x32,0x30,0x60,0x59,0x2D,0x60,0x4D,0x2D,
				  0x60,0x44,0x20,(byte)0xD0,(byte)0xC7,(byte)0xC6,(byte)0xDA,0x60,0x57,0x60,0x48,0x3A,0x60,0x4E,0x3A,0x60,0x53,0x03};
		 return Base64.getEncoder().encodeToString(time);
	}
	
	
	public static void main(String[] args) throws Exception{
//		String s = JSON.toJSONString(getLEDViewHashMap(1,0));
//		System.out.println(s);
	}
}
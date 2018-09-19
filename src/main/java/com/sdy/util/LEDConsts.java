package com.sdy.util;

/**
 * 屏幕显示常量类
 * @author rex
 */
public class LEDConsts {
	
	//开闸
	public static String CAMERA_OPEN="ok";		//开闸
	public static String CAMERA_NOT_OPEN="no";		//不开闸
	
	//串口号
	public static int LED_CHUANKOU_NUM=1;		//1号串口
	//屏幕行数
	public static byte LED_TEXT_LINE_ONE=0x21;	//第1行
	public static byte LED_TEXT_LINE_TWO=0x22;	//第2行
	public static byte LED_TEXT_LINE_THREE=0x23;//第3行
	public static byte LED_TEXT_LINE_FOUR=0x24;	//第4行
	public static byte LED_TEXT_LINE_FIVE=0x25;	//第5行	语音行
	public static byte LED_TEXT_LINE_ALL=(byte)0xe8;//所有行
	
	
	//命令
	public static byte LED_COMMAND=0x25;		//固定值
	//扇区
	public static byte LED_LS_SHANQU=0x10;		//临时扇区,也就是临时展示，过了就换成固定内容
	public static byte LED_GD_SHANQU=0x21;		//固定扇区，一直展示
	
	//开始第一行显示
	public static byte LED_BEGIN_ONE_VIEW=0x0C;	//固定值
	//进入模式
	public static byte LED_IN_TYPE_NOW=0x20;	//立即显示
	public static byte LED_IN_TYPE_RIGHT_TO_LEFT=0x21;	//从右往左
	public static byte LED_IN_TYPE_LEFT_TO_RIGHT=0x22;	//从左往右
	//停留模式
	public static byte LED_WAIT_TYPE=0x20;		//固定值
	//停留时间
	public static byte LED_WAIT_TIME_ONE=0x21;		//停留1秒
	public static byte LED_WAIT_TIME_FIVE=0x25;		//停留5秒
	public static byte LED_WAIT_TIME_EIGHT=0x28;		//停留8秒
	//退出模式
	public static byte LED_OUT_TYPE=0x20;		//固定值
	//文档长度
	public static byte LED_DOC_LENGTH=0x29;		//固定值
	
	//颜色
	public static byte LED_COLOR_RED=0x10;		//红色
	public static byte LED_COLOR_GREEN=0x11;	//绿色
	public static byte LED_COLOR_YELLOW=0x12;	//黄色
	
	
}
package com.sdy.controller.test.junit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于JUnit测试
 * @author Rex
 */

public class JUnitService {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	
	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static String getString(String s) {
		return s;
	}
}

package com.sdy.controller.test.junit;


import org.junit.Assert;
import org.junit.Test;

import com.sdy.util.DateUtil;
/**
 * junit测试
 * @author Rex
 */
public class JunitTest {
	
	/**
	 * 测试方法返回的是不是false
	 */
	@Test
	public void testIsValidDate() {
		Assert.assertFalse(JUnitService.isValidDate("2018-s7-14"));//通过
		//assertFalse(JUnitService.isValidDate("2018-7-14"));//不通过
	} 

	/**
	 * 测试与方法返回的值相比是否一样
	 */
	@Test
	public void testGetYear() {
		Assert.assertEquals("2018",JUnitService.getYear());//通过
		//assertEquals("2011",JUnitService.getYear());//不通过
	} 
	/**
	 * 测试方法是否返回的是null
	 */
	@Test
	public void testGetString() {
		Assert.assertNotNull(JUnitService.getString("s"));//通过
		//assertNotNull(JUnitService.getString(null));//不通过
	} 
	
	/**
	 *	Assert 类中的一些有用的方法列式如下：
		void assertEquals(boolean expected, boolean actual)
		检查两个变量或者等式是否平衡
		void assertTrue(boolean expected, boolean actual)
		检查条件为真
		void assertFalse(boolean condition)
		检查条件为假
		void assertNotNull(Object object)
		检查对象不为空
		void assertNull(Object object)
		检查对象为空
		void assertSame(boolean condition)
		assertSame() 方法检查两个相关对象是否指向同一个对象
		void assertNotSame(boolean condition)
		assertNotSame() 方法检查两个相关对象是否不指向同一个对象
		void assertArrayEquals(expectedArray, resultArray)
		assertArrayEquals() 方法检查两个数组是否相等
	 */
	
}

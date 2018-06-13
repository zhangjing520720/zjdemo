/*
 * 文件名：BaseData.java
 * 版权：Copyright 2007-2017 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BaseData.java
 * 修改人：Administrator
 * 修改时间：2017年3月23日
 * 修改内容：新增
 */
package com.sdy.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * TODO 添加类的一句话简单描述.
 * 
 * @author Administrator
 */
public class BaseData {

	
	/**
	 * @param fastjson
	 */
	protected static JSONObject json = new JSONObject();

	/**
	 * @param fastjson
	 *            JSONArray
	 */
	protected JSONArray jsonArray = new JSONArray();
	//
	// /**
	// * 注入配置文件节点.
	// */
	// @Resource(name = "properties")
	// public Properties businessParam;

}

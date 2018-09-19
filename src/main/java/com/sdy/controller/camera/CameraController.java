package com.sdy.controller.camera;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sdy.controller.base.BaseController;
import com.sdy.util.LEDConsts;
import com.sdy.util.LEDUtil;
import com.sdy.util.PageData;

//@Scope(value ="prototype")
@RestController
@RequestMapping("/camera")
public class CameraController extends BaseController{
	

	int i=0;
	
	/**
	 *相机识别上报
	 */
	@RequestMapping(value= "/entrance",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String entrancePost(HttpServletRequest request,@RequestBody PageData paramPd){
		
		System.out.println("POST相机上报："+json.toJSONString(paramPd));
		
		PageData resultPd = new PageData();
		PageData responseAlarmInfoPlatePd = new PageData();
		responseAlarmInfoPlatePd.put("info", "ok");
		responseAlarmInfoPlatePd.put("plateid", "川A26234");
		responseAlarmInfoPlatePd.put("channelNum", 0);
		responseAlarmInfoPlatePd.put("manualTigger", "no");
		responseAlarmInfoPlatePd.put("is_pay", "true");
		responseAlarmInfoPlatePd.put("serialData", new ArrayList());
		resultPd.put("Response_AlarmInfoPlate", responseAlarmInfoPlatePd);
		System.out.println("相机上报返回："+json.toJSONString(resultPd));
		return json.toJSONString(resultPd);
	}
	
	
	/**
	 *心跳
	 */
	@RequestMapping(value= "/heart",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String heartPost(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Thread.sleep(10000);
		PageData pd = new PageData(request);
		System.out.println("POST心跳"+json.toJSONString(pd));
		PrintWriter pw = response.getWriter();
		PageData resultPd = new PageData();
		PageData serialPd = new PageData();
		resultPd.put("isOpen", LEDConsts.CAMERA_OPEN);
		resultPd.put("info", LEDConsts.CAMERA_OPEN);
		if((i%2)==1){//奇数是发送金额
			serialPd.put("amount", 111);
			serialPd.put("qrUrl", "http://www.baidu.com");
			
		}else{		//偶数时发送余位
			serialPd.put("remainingSpace", 20);
		}
		resultPd.put("serial", serialPd);
//		String returnStr =json.toJSONString(resultPd);
		String returnStr ="{\"Response_AlarmInfoPlate\":{\"info\":\"ok\"}}";
		System.out.println(request.getParameter("serialno")+"返回："+returnStr);
		pw.write(returnStr);
		pw.flush();
		pw.close();
		i++;
		return null;
	}
	
}

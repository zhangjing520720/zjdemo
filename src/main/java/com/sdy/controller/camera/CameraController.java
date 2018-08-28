package com.sdy.controller.camera;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sdy.controller.base.BaseController;
import com.sdy.util.PageData;


@RestController
@RequestMapping("/zs")
public class CameraController extends BaseController{
	


	
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
		
		return json.toJSONString(resultPd);
	}
	
	
	/**
	 *心跳
	 */
	@RequestMapping(value= "/heart",headers = "Accept=*/*", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String heartPost(HttpServletRequest request)throws Exception{
		System.out.println("POST心跳"+request.getParameter("serialno"));
		
		return "success";
	}

}

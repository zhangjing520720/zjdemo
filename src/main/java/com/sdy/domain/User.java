package com.sdy.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.information.user.View;

public class User implements Serializable{

	@JsonView(View.Summary.class) 
	private String id;
	@JsonView(View.Summary.class) 
	private String name;
	@JsonView(View.SummaryWithDetail .class) 
	private String sex;
	
	private String birthDay;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//获取详情
	public String getInfo(String id){
		
		return "zhangjing";
	}
	
	//获取详情
	public String getInfo(String id,String name){
		
		return "heli";
	}
	
	
	 public static void main(String[] args) {
		User user =new User();
		
		String name = user.getInfo("1");
		 System.out.println(name);
		 AdminUser au = new AdminUser();
		 String name2 = au.getInfo("1");
		 System.out.println(name2);
		 
	}
}

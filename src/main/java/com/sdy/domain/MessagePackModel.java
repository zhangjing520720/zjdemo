package com.sdy.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.msgpack.annotation.Message;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdy.controller.information.user.View;

@Message
public class MessagePackModel {

	private String id;
	private String name;
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

	
	
	
	 
}

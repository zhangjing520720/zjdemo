package com.sdy.controller.test.jicheng;


public class Animal {
	
	int age;
	String name;
	public  String getTest(){
		
		return "animal";
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void cry(){
		System.out.println("我不知道叫啥");
	}
	public void eat(){
		System.out.println("我不知道吃啥");
	}
}

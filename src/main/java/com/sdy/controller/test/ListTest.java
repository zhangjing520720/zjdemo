package com.sdy.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdy.model.PageData;

/**
 * List集合测试
 * @author Rex
 */
public class ListTest<I,N> {
	
	private I id;
	private N name;
	
	public static void main(String[] args) {
		
		PageData  pageData = new PageData();
		HashMap hashMap = new HashMap();
		List list= new ArrayList();
		list.add("122");
		
		
		
		Map<HashMap, HashMap> map = new HashMap();
		map.put(pageData, new HashMap());
		
		System.out.println(map.get(2));
		
		
		List<String> list0= new ArrayList();
		list0.add("2");
		List<Object> list1= new ArrayList();
		list1.add("1");
		list1.add(1);
		list1.add(hashMap);
		list1.add(list0);
		new ListTest().get(list0);
		
		
		ListTest<String,String> lt  =new ListTest<>();
		lt.setId("1s");
		lt.setName("Rex");
		
		System.out.println(lt.getId());
		System.out.println(lt.getName());
	}
	public void setId(I id){
		this.id = id;
	}
	
	public I getId(){
		return id;
	}
	
	public void setName(N name){
		this.name = name;
	}
	
	public N getName(){
		return name;
	}
	
	public void get(List<Object> list){
		System.out.println(list.toString());
	}
	
}

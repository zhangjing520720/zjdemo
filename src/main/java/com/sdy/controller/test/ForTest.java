package com.sdy.controller.test;

import java.util.ArrayList;
import java.util.List;

public class ForTest {
	
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		for(int i=0;i<10000000;i++){
			list.add(i+"");
		}
		
		
		System.out.println(System.currentTimeMillis());
		long begin1 =System.currentTimeMillis();
		for(int i=0,length=list.size();i<length;i++){
			String s = list.get(i);
		}
		System.out.println(System.currentTimeMillis()-begin1);
		
		
		
		System.out.println(System.currentTimeMillis());
		long begin =System.currentTimeMillis();
		for(int i=0;i<list.size();i++){
			String s = list.get(i);
		}
		System.out.println(System.currentTimeMillis()-begin);
	}

}

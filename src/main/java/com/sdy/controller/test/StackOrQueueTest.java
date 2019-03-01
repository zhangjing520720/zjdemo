package com.sdy.controller.test;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import sun.misc.Unsafe;
public class StackOrQueueTest {
	
	public static void main(String[] args) {
		Stack<String>  s = new Stack<>();
		s.push("1");
		s.push("2");
		s.push("3");
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		
		Queue<String>  q = new LinkedBlockingQueue<String>();
		q.add("1");
		q.add("2");
		q.add("3");
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		
		Unsafe u = Unsafe.getUnsafe();
 		
	}

}

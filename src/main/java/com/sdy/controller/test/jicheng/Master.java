package com.sdy.controller.test.jicheng;

/**
 * 主人
 * @author Rex
 */
public class Master {
	
	//喂食
	public void feed(Animal a ,String shiwu){
		a.cry();
		a.eat();
		System.out.println("喂"+shiwu);
	}
	
	//喂狗
	public void feed(Dog a ,String shiwu){
		a.cry();
		a.eat();
		System.out.println("喂"+shiwu);
	}
	//喂猫
	public void feed(Cat a ,String shiwu){
		a.cry();
		a.eat();
		System.out.println("喂"+shiwu);
	}
	public static void main(String[] args) {
		Master m = new Master();
		m.feed(new Dog(),"骨头");
		m.feed(new Cat(),"鱼");
		
		Cat cat = new Cat();
		String gettest = cat.getTest();
		System.out.println(gettest);
		
	}
}

package com.luml.java.javaclass.studyD10;

class Demo13_Abstract {
	public static void main(String[] args) {
		Dog d = new Dog();
		d.叫();
		d.跑();
		Cat c = new Cat();
		c.叫();
		//Animal a = new Animal();			抽象类是不可以创建对象
		//a.叫();							调用抽象方法没有任何意义
	}
}


abstract class Animal {
	public abstract void 叫();
	public abstract void 吃();
	//抽象类中也可以定义非抽象方法,而且需要由子类对象调用
	public void 跑() {
		System.out.println("动物跑");
	}
}

class Dog extends Animal {
	@Override
	public void 叫() {
		System.out.println("汪汪");
	}
	@Override
	public void 吃() {
		System.out.println("狼吞虎咽");
	}
}

class Cat extends Animal {
	@Override
	public void 叫() {
		System.out.println("喵喵");
	}
	@Override
	public void 吃() {
		System.out.println("细嚼慢咽");
	}
}
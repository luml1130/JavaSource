package com.luml.java.javaclass.studyD10;

class Demo4_Extends {
	public static void main(String[] args) {
		Child4 c = new Child4();
		c.speak();
	}
}

class Parent4 {
	String name;
	public Parent4() {
		name = "张三";
		System.out.println("Parent");
	}
}

class Child4 extends Parent4 {
	public Child4() {
		//super();		每一个构造函数中都会默认有一个super语句,用来调用父类的空参数的构造函数
		System.out.println("Child");
	}

	public void speak() {
		System.out.println(name);
	}
}



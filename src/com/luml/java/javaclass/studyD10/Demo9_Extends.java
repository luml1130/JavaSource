package com.luml.java.javaclass.studyD10;

class Demo9_Extends {
	public static void main(String[] args) {
		/*Child9 c = new Child9();
		c.print();*/
		Child9.print();
	}
}

class Parent9 {
	public static void print() {
		System.out.println("parent");
	}
}

class Child9 extends Parent9 {					//静态只能覆盖静态
	public static void print() {
		System.out.println("child");
	}
}

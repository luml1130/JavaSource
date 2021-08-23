package com.luml.java.javaclass.studyD10;

class Demo1_Extends {
	public static void main(String[] args) {
		Child1 c = new Child1();
		c.print();
	}
}

class Parent1 {
	int num = 10;
}

class Child1 extends Parent1 {
	int num = 20;
	public void print() {
		//int num = 30;
		System.out.println(this.num);			//打印本类的成员变量,this可以区分本类成员变量和局部变量重名
		System.out.println(super.num);			//打印父类的成员变量,super可以区分子父类成员变量重名
	}
}

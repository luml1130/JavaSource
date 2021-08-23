package com.luml.java.javaclass.studyD10;

class Demo2_Extends {
	public static void main(String[] args) {
		Child2 c = new Child2();
		c.speak();
	}
}

class Parent2 {
	void speak() {
		System.out.println("Parent");
	}

	public void print() {
		System.out.println("111111111");
	}
}

class Child2 extends Parent2 {
	@Override
	public void speak() {
		System.out.println("Child");
	}
}

/*
重写
1,父类权限不能是private
2,子类的权限必须大于等于父类的权限
3,除了权限修饰符,其他要一模一样
*/

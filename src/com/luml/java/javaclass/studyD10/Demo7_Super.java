package com.luml.java.javaclass.studyD10;

class Demo7_Super {
	public static void main(String[] args) {
		Child7 c = new Child7();
		c.print();
		c.method();
	}
}

class Parent7 {
	public void print() {
		System.out.println("Parent");
	}
}

class Child7 extends Parent7 {
	@Override
	public void print() {
		System.out.println("Child");
	}

	public void method() {
		super.print();						//在子类中调用父类的方法用super
	}
}


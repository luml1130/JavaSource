package com.luml.java.javaclass.studyD10;

class Demo10_Final {
	public static void main(String[] args) {
		Child c = new Child();
		//c.print();
		c.method();
	}
}

class Parent {
	public final void print() {
		System.out.println("调用系统底层资源");
	}

	public void method() {
		System.out.println("method");
	}
}

class Child extends Parent {
	/*public void print() {
		System.out.println("哈哈,底层资源被我干掉了");
	}*/
	final int NUM = 10;
	final double PI = 3.14;
	@Override
	public void method() {

		System.out.println(NUM);
	}
}

/*
1,final 修饰的类,不能被继承(丁克)
2,final 修饰的方法,不能被覆盖
3,final 修饰的变量,是常量,里面存储的值不能被改变
*/
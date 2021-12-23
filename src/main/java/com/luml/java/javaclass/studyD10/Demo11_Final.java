package com.luml.java.javaclass.studyD10;

class Demo11_Final {
	public static void main(String[] args) {
		Demo11 d = new Demo11();
		d.print();

		//Demo11 d2 = new Demo11(100);
		//d2.print();
	}
}

class Demo11 {
	final int NUM;

	public Demo11() {
		NUM = 20;
	}
	
	public Demo11(int xxx) {
		NUM = 30;
	}
	public void print() {
		System.out.println(NUM);
	}
}
/*
fianl修饰的变量即常量,有两种初始化的方式
1,显示初始化,直接赋值
2,构造函数初始化
*/
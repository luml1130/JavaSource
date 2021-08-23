package com.luml.java.javaclass.studyD10;

class Demo14_Abstract {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}

abstract class Demo14 {						//如过一个抽象类中,没有定义抽象方法,全是非抽象方法
	public void print1() {					//这样做目的只有一个,就是不让其他类创建这个类对象
		System.out.println("Hello World!1111111");
	}
	public void print2() {
		System.out.println("Hello World!222222222");
	}
}


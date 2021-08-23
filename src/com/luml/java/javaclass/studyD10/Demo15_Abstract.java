package com.luml.java.javaclass.studyD10;

class Demo15_Abstract {
	public static void main(String[] args) {
		//Demo15 d = Demo15.d;
	}
}

abstract class Demo15 {
	//private Demo15(){}				//当把构造函数私有,就不可以在其他类创建本类对象了
	//static Demo15 d = new Demo15();		//0x0011
	//Demo15 d = new Demo15();			//只要是类上加abstract就不能写前面这句话

	public  void print() {			//因为抽象类不能创建对象,如果想使用他里面的方法,必须由子类完成
		System.out.println("11111");
	}
}
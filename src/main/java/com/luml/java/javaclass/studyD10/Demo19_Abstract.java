package com.luml.java.javaclass.studyD10;

class Demo19_Abstract {
	public static void main(String[] args) {
		/*Demo19 d = new Demo19();
		d.print();*/
	}
}

abstract class Demo19 {
	//private abstract void print();
	public abstract void print();
}


class DemoA extends Demo19 {
	@Override
	public void print() {
		System.out.println("Hello World!");
	}
}
//错误: 非法的修饰符组合: abstract和final
//final修饰的方法,不让子类重写,abstract修饰的方法就是为了让子类重写,所以非常矛盾
//错误: 非法的修饰符组合: abstract和final
//被静态修饰的方法,可以类名.调用,类名.调用抽象方法没有意义
//错误: 非法的修饰符组合: abstract和private
//private 是不让其他类包括子类访问到,abstract就是为了让子类看到并重写
package com.gof.structural.Decorator;

import java.io.IOException;
//装饰者模式

/**
 * 你有个对象有个功能 是在N年前建立的，如今你觉得功能不够用了 写个类把对象传进来就可以解决问题了 如果这个功能写错了 我就把自己写的功能去掉又不影响以前的功能灵活性相当强的。
装饰模式比继承要灵活。避免了继承体系臃肿。
而且降低了类于类之间的关系。
装饰类因为增强已有对象，具备的功能和已有的是相同的，只不过提供了更强功能。
所以装饰类和被装饰类通常是都属于一个体系中的。
 *装饰纸模式只需要在新建一个对象传入以前对象 就可以了 
 */
public class decorate {
		private static final  Person s=null;
		public static void main(String[] args) throws IOException {		
			Person2 s=new Person2();
			s.code();
			ItcastStudent is=new ItcastStudent( s);
			is.code();
		}	
}

interface Coder{
	public void code();
}

class Person2 implements Coder{
	@Override
	public void code() {
		System.out.println("javase");
		System.out.println("javaee");
	}	
}

class ItcastStudent implements Coder{
	private Person2 s;
	public ItcastStudent  (Person2 s){
		this.s=s;
	}
	@Override
	public void code() {
		s.code();
		System.out.println("yunjs");
	}	
}



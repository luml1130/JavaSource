package com.luml.java.exception.tryTest;

public class Demo5_Exception {
	public static void main(String[] args) throws Exception {
		Div2 d = new Div2();
		int x = d.div(10, -5);
		System.out.println(x);
	}
}

class Div2 {
	public int div(int a,int b) throws Exception {
		if(b <= 0) {
			NegativeException n = new NegativeException("除数不能为负数和零");
			throw n;
			//Exception e = new Exception("除数不能为负数和零");
			//throw e;
		}
		return a / b;				
	}
}

class NegativeException extends Exception {
	public NegativeException(String message) {
		super(message);
	}
}

/*
 * 为什么要自定义异常
 * 因为当项目很大时,每一个模块所报的异常不同
 * 1,很有可能是java中根本就没有定义的,所以需要我们自己定义
 * 2,自定义异常,我们可以根据具体的名字找到具体的异常,并对其进行处理
 */

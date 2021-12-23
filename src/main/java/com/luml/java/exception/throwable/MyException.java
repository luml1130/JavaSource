package com.luml.javase.exception.throwable;

public class MyException extends Exception {
	//自定义异常通常继承Exception，在有参构造方法中添加异常信息
	public MyException(String message) {
		super(message);
	}
}

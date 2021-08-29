package com.luml.java.exception.customer;

public class AppException extends RuntimeException{

	public AppException() {
		super();
	}
	public AppException(String msg) {
		super(msg);
	}
	public AppException(String msg,Throwable t) {
		super(msg,t);
	}
	
}

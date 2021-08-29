package com.luml.java.exception.tryTest;

public class Demo7_Exception {
	public static void main(String[] args) {
		Demo d = new Demo();
		int num = d.print();
		System.out.println(num);
	}
}

class Demo {
	public int print() {
		int x = 10;
		try {
			System.out.println(1/0);
			return x;
		} catch (Exception e) {
			x = 20;
			return x;
		} finally {
			x = 30;
		}
		//return 0;
	}
}

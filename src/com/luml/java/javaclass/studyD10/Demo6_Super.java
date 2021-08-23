package com.luml.java.javaclass.studyD10;

class Demo6_Super {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}

class Child6 {
	int num = 10;
	public Child6() {
		this(10);
	}

	public Child6(int num) {
		this.num = num;
	}
	public void speak() {
		int num = 20;
		System.out.println(this.num);
		this.method();
	}

	public void method() {
		System.out.println("Hello World!");
	}
}
	
package com.luml.java.javaclass.studyD10;

class Demo8_Super {
	public static void main(String[] args) {
		Student8 s = new Student8();
	}
}


class Person8 {
	private String name;

	/*public Person() {
		System.out.println("父类空参的");
	}*/

	public Person8(String name) {
		this.name = name;
		System.out.println("父类有参的");
	}
}

class Student8 extends Person8 {
	public Student8() {
		//super("zhangsan");
		this("zhangsan");
		System.out.println("子类空参的");
	}

	public Student8(String name) {
		super(name);
		System.out.println("子类有参的");
	}
}
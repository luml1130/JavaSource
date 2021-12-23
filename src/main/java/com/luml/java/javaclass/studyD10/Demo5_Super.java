package com.luml.java.javaclass.studyD10;

class Demo5_Super {
	public static void main(String[] args) {
		Student s = new Student("关俊龙",20);
		s.speak();
	}
}

class Person5 {
	private String name;
	private int age;

	public Person5() {									//空参数的构造函数
		System.out.println("空参数的父类的构造函数");
	}

	public Person5(String name, int age) {				//有参数的构造函数
		this.name = name;								//name 张三 age 23
		this.age = age;
		System.out.println("有参数的父类的构造函数");
	}

	public void setName(String name) {					//设置名字的方法
		this.name = name;
	}

	public String getName() {							//获取名字的方法
		return name;
	}

	public void setAge(int age) {						//设置年龄的方法
		this.age = age;
	}

	public int getAge() {								//获取年龄的方法
		return age;
	}
}

class Student extends Person5 {
	public Student() {
		super("张三",23);
		System.out.println("空参数的子类的构造函数");
	}

	public Student(String name,int age) {			//name 关俊龙 age 20
		this();
		System.out.println("有参数的子类的构造函数");
	}

	public void speak() {
		System.out.println("我叫" + getName() + ",我的年龄是" + getAge());
	}
}

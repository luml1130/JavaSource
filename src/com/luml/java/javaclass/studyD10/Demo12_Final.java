package com.luml.java.javaclass.studyD10;

class Demo12_Final {
	public static void main(String[] args) {
		//final修饰引用数据类型的时候,不能修改的是地址值,可以修改的是对象中的属性
		final Person p1 = new Person("张三",23);	//0x0011
		//p1 = new Person("李四",24);
		p1.setName("李四");
		p1.setAge(24);
		p1.speak();
	}
}

class Person {
	private String name;
	private int age;

	public Person(){}

	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public void speak() {
		System.out.println(name  + "..." + age);
	}
}

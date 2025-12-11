package com.luml.domain;

public class Person { // ctrl + m全屏
	private String name;
	private String bigName;
	private int age;

	public Person() {
		super();

	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getBigName() {
		return bigName;
	}

	public void setAge(int age) {
		if (age > 0 && age <= 130) {
			this.age = age;
		} else {
			// Exception e = new Exception("年龄非法");
			// throw e;
			throw new RuntimeException("年龄非法");
		}
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}


}

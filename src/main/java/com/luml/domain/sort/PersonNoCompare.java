package com.luml.domain.sort;

import lombok.Data;

@Data
public class PersonNoCompare {
	private int id;
	private int age;
	private String fullName;

	public PersonNoCompare() {
		super();

	}

	public PersonNoCompare(int id, int age, String fullName) {
		this.id = id;
		this.age = age;
		this.fullName = fullName;
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
		return "Person [age=" + age + ", name=" + fullName +  "]";
	}


}

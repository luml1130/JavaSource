package com.luml.java.exception.tryTest;


import com.luml.javase.pojo.Person;

public class Demo3_Exception {
	public static void main(String[] args) throws Exception{
		Person p = new Person("张三", 23);
		p.setAge(-17);
		System.out.println(p.getAge());
	}
}



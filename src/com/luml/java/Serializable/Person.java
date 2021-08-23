package com.luml.java.Serializable;

import java.io.Serializable;

public class Person implements Serializable{
	
    /**
	 * @author maclian 2015年8月13日 下午5:31:55
	 */
	private static final long serialVersionUID = 1L;
	private int age;
    private String name;
    private String sex;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

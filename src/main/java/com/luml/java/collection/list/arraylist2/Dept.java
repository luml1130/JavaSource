package com.luml.java.collection.list.arraylist2;

public class Dept {
	private int salary;
	private int comm;
	
	public Dept(int salary) {
		this.salary = salary;
		comm = 0;
	}
	
	public Dept(int salary, int comm) {
		this.salary = salary;
		this.comm = comm;
	}
	
	public int calcMoney() {
		return salary+comm;
	}
}

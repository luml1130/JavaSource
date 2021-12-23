package com.luml.java.collection.list.arraylist2;

public class Employee {
	private Dept dept;
	private int money;
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void calcMoney() {
		setMoney(dept.calcMoney());
	}
	
	public static void main(String[] args) {
		Employee emp1 = new Employee();
		emp1.setDept(new Reserch());
		Employee emp2 = new Employee();
		emp2.setDept(new Administration());
		
		emp1.calcMoney();
		emp2.calcMoney();
		System.out.println("emp1:"+emp1.getMoney());
		System.out.println("emp2:"+emp2.getMoney());
	}
}

package com.luml.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class Employee {
	private String name;
	private String dept;
	
	public Employee(String name, String dept) {
		this.name = name;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public static void main(String[] args) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		emps.add(new Employee("小王", "行政"));
		emps.add(new Employee("小李", "教学"));
		emps.add(new Employee("小张", "后勤"));
		emps.add(new Employee("小刘", "咨询"));
		emps.add(new Employee("小高", "财务"));
		
		Iterator<Employee> iter = emps.iterator();
		while(iter.hasNext()) {
			Employee emp = iter.next();
			if(emp.getName().equals("小高")) {
				System.out.println(emp.getDept());
			}
			//System.out.println("员工姓名："+iter.next().getName()+"员工部门："+iter.next().getDept());
		}
	}
}

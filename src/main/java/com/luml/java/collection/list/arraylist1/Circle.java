package com.luml.java.collection.list.arraylist1;

public class Circle extends Shape {
	public static final double PI = Math.PI;
	private double ridus;
	
	public Circle(){
		super();
	}
	
	public Circle(double ridus){
		this.ridus = ridus;
	}
	
	public void setRidus(double ridus) {
		this.ridus = ridus;
	}
	public double getRidus() {
		return ridus;
	}
	@Override
	public double countArea(){
		double area = (int)(PI*this.ridus*this.ridus*100)/100.0;
		//System.out.println("面积为："+area);
		return area ;
	}
	@Override
	public double countGirth(){
		double girth = (int)(2*PI *this.ridus*100)/100.0;
		//System.out.println("周长为："+girth);
		return girth;
	}


}

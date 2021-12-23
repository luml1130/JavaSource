package com.luml.java.collection.list.arraylist1;

public class Rect extends Shape{
	
	private double length;
	private double width;
	
	public Rect(){
		
	}
	public Rect(double length,double width){
		this.length = length;
		this.width = width;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getLength() {
		return length;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getWidth() {
		return width;
	}
	@Override
	public  double countArea(){
		double area = length*width;
		//System.out.println(area);
		return area;
	}
	@Override
	public double countGirth(){
		double girth = 2*(length+width);
		//System.out.println(girth);
		return girth;
	}

}

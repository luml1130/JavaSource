package com.luml.java.collection.list.arraylist1;

public class ObjectArray {
	
	public static void main(String[] args) {
		Rect r1 = new Rect(10, 20);
		Rect r2 = new Rect(15, 23);
		Rect[] arrRect = {r1, r2, new Rect(11, 24)};
		System.out.println(arrRect[2].countGirth());
		for(Rect r : arrRect) {
			System.out.println(r.countArea());
		}
		
		System.out.println("------------");
		Shape[] arrShape = {
			r1, r2, new Circle(3), new Square(5)
		};
		for(Shape r : arrShape) {
			System.out.println(r.countArea());
		}
	}
}

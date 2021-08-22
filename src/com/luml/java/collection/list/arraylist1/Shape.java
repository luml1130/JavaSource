package com.luml.java.collection.list.arraylist1;

/*
 * 类声明中加上abstract关键字，这个类就是抽象类
 * 如果一个类里有抽象方法，这个类一定要定义成抽象类
 * 抽象类不能实例化对象
 * 抽象类的非抽象子类，必须实现抽象类中所有的抽象方法
 */
public abstract class Shape {
	/*
	 * 抽象方法，方法的声明中加关键字abstract，方法以分号结束
	 */
	public abstract double countArea();
	public abstract double countGirth();
}
	
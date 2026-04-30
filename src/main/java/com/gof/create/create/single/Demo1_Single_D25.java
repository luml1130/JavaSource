package com.gof.create.create.single;

public class Demo1_Single_D25 {
	public static void main(String[] args) {
		/*
		 * Single s1 = Single.s; System.out.println(s1); Single.s = null; Single
		 * s2 = Single.s; System.out.println(s2); System.out.println(s1 == s2);*/
		Single s1 = Single.getInstance();
		Single s2 = Single.getInstance();
		System.out.println(s1 == s2);
		Single2 s3 = Single2.getInstance();
		Single2 s4 = Single2.getInstance();
		System.out.println(s3 == s4);
		Single3 s5 = Single3.s;
		Single3 s6 = Single3.s;
		System.out.println(s5 == s6);
	}
}

/**
 * 饿汉式
 * 第一步:私有构造函数
 * 第二步:创建本类对象
 * 第三步:对外提供公共的访问方法，多线程并发访问的时候要加锁
 */
class Single {
	private Single() {}
	private static Single s = new Single();
	public static synchronized Single getInstance() {
		return s;
	}
}

/**
 * 懒汉式单例类.在第一次调用的时候实例化自己
 * 第一步:私有构造函数
 * 第二步:声明一个本类的引用
 * 第三步:对外提供公共的访问方法
 */
class Single2 {
	private Single2() {}
	private static Single2 s;
	public static Single2 getInstance() {
		if(s == null) {
			//12
			s = new Single2();
		}
		return s;
	}
}


/**
 * 第三种模式
 * 第一步:私有构造函数
 * 第二步:创建本类对象
 */
class Single3 {
	private String name;
	private Single3() {}
	public final static Single3 s = new Single3();
}

package com.luml.java.javaclass.data.String.D15String;

public class Demo1_String {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//demo1(); 	
		//demo2();
//		demo3();	
//		demo4();
		demo5(); 	
		
	}

	private static void demo5() {
		String s1 = "ab";
		String s2 = "abc";
		String s3 = s1 + "c";					//字符串用+与其他数据相连接,会调用StringBuffer或StringBuilder中的append方法
		System.out.println(s3 == s2); 													
		System.out.println(s3.equals(s2));
	}

	private static void demo4() {
		byte b = 3 + 4 + 5;
		String s1 = "a" + "b" + "c";			//java有常量优化机制
		String s2 = "abc";
		System.out.println(s1 == s2); 
		System.out.println(s1.equals(s2));
	}

	private static void demo3() {
		String s1 = new String("abc");			
		String s2 = "abc";
		System.out.println(s1 == s2);		
		System.out.println(s1.equals(s2));
	}

	private static void demo2() {
		String s1 = new String("abc");		//在内存中创建了几个对象?创建两个对象
	}

	private static void demo1() {
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2); 					
		System.out.println(s1.equals(s2));
	}

}

package com.luml.java.javaclass.data.String.D15String;
/*
 * 4,替换。
		4.1 String replace(oldChar,newChar);	//将newChar替换OldChar,如果OldChar不存在,原字符串直接赋值给替换后字符串
		4.2 String replace(string,string);		
		
	5,切割。
		String[] split(regex);					//通过regex切割字符串,切割后会产生一个字符串数组
		String s = "金三胖 郭美美 李天一";
		String[] arr = s.split(" ");
		
	6,去除字符串两空格。
		String trim();							
		
	7,比较
		String str = "ab";
		String str1 = "bc";
		int num = str.compareTo(str1);			//如果str比str1大的话,返回的正数
	
 */
public class Demo7_StringMethod {

	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args) {
//		demo1();		
//		demo2();
		demo3();
		String s1 = "abc";
		String s2 = "bcd";
		int num = s1.compareTo(s2);
		System.out.println(num);
	}

	public static void demo3() {
		String s1 = "   it cast   ";
		System.out.println(s1.trim());
	}

	public static void demo2() {
		String s = "金三胖.郭美美.李天一";
		String[] arr = s.split("\\.");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void demo1() {
		String s1 = "itcast heima";
		String s2 = s1.replace('i', 'x');		//找到匹配的oldChar就用newChar替换
		System.out.println(s2);					//找不到就不替换
		String s3 = s1.replace("itcast", "good");
		System.out.println(s3);
	}

}

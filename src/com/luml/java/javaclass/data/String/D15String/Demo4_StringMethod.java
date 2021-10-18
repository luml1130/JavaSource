package com.luml.java.javaclass.data.String.D15String;

import java.util.Scanner;

public class Demo4_StringMethod {

	/**
	 * 1,判断 1.1 boolean equals(Object);
	 * //判断传入的字符串是否与调用的字符串字符序列是否相同,相同就返回true否则false 1.2 boolean
	 * equalsIgnoreCase(string);
	 * //判断传入的字符串是否与调用的字符串字符序列是否相同,不区分大小写,相同就返回true否则false 1.3 boolean
	 * contains(string); //判断传入的字符串是否被调用的字符串包含 1.4 boolean startsWith(string);
	 * //判断调用的字符串是否以传入的字符串开头 1.5 boolean endsWith(string); //判断调用的字符串是否以传入的字符串结尾
	 * 1.6 boolean isEmpty(); //判断字符串是否为空
	 */
	public static void main(String[] args) {
		// demo1();
		// demo2();
		// demo3();
		// demo4();
		/*
		 * 简单登录 用户名和密码都是admin,等待键盘录入,如果录入的用户名和密码都正确,提示欢迎admin登录
		 * 如果录入的不正确,有三次机会,前两次提示您还有几次机会,请重新输入,最后一次提示你的错误次数已到,明天再来吧
		 */
		Scanner sc = new Scanner(System.in);
		for (int x = 0; x < 3; x++) {
			System.out.println("请输入用户名:");
			String user = sc.nextLine();
			System.out.println("请输入密码:");
			String password = sc.nextLine();
			if (user.equals("admin") && password.equals("admin")) {
				System.out.println("欢迎" + user + "登录");
				break;
			} else {
				if(x == 2) {
					System.out.println("错误次数已到,明天再来吧");
				}else {
					System.out.println("您还有" + (2 - x) + "次机会,请重新输入:");
				}
			}
		}
	}

	public static void demo4() {
		String s1 = "";
		System.out.println(s1.isEmpty());
	}

	public static void demo3() {
		String s1 = "itcastabc";
		String s2 = "it";
		String s3 = "abc";
		System.out.println(s1.startsWith(s2));
		System.out.println(s1.endsWith(s3));
	}

	public static void demo2() {
		String s1 = "abcde";
		String s2 = "abcdeitcast";
		System.out.println(s1.contains(s1)); // 判断是否包含
	}

	public static void demo1() {
		String s1 = "abc";
		String s2 = "abc";
		String s3 = "ABc";
		System.out.println(s1.equals(s2)); //equals判断的是相同的字符序列,如果相同就返回true
//		System.out.println(s1.equalsIgnoreCase(s3));// 不区分大小写
	}

}

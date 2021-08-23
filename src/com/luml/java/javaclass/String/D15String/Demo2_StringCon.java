package com.luml.java.javaclass.String.D15String;

import java.io.UnsupportedEncodingException;

public class Demo2_StringCon {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
//		demo1();
//		demo2();
//		demo3();
		//demo4();
		byte[] arr = {-28,-67,-96,-27,-91,-67,-28,-67,-96,-27,-91,-67 };
		String str = new String(arr,"UTF-8");
		System.out.println(str);
	}

	public static void demo4() throws UnsupportedEncodingException {
		String str = "你好你好";
		byte[] arr = str.getBytes("UTF-8");		//指定的编码表编码
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");		//-28 -67 -96 -27 -91 -67 -28 -67 -96 -27 -91 -67 
		}
	}

	public static void demo3() throws UnsupportedEncodingException {
		byte[] arr = {-60,-29,-70,-61,-60,-29,-70,-61 };
		String str = new String(arr,"GBK");		//指定的编码表解码
		System.out.println(str);
	}

	public static void demo2() {
		String str = "你好你好";      //编码,让计算机看的懂的,用默认的编码表,将字符串转换成字节数组
		byte[] arr = str.getBytes();			//编码-60 -29 -70 -61 -60 -29 -70 -61 
		System.out.println(arr.length);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void demo1() {
		byte[] arr = {110,111,112};
		String str = new String(arr);			//解码,把我们看不懂的变成我们能看懂的
		System.out.println(str);
	}

}

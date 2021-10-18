package com.luml.java.javaclass.data.String.D15String;

public class Demo3_StringCon {

	/**
	 * @param args
	 * 获取一个字符中的大写字母,小写字母,数字和其他字符的个数
	 */
	public static void main(String[] args) {
//		 demo1();
		 demo2();
//		demo3();
	}

	public static void demo3() {
		// String str = "ABCDE123456abcd!@#$";
		int big = 0;
		int small = 0;
		int num = 0;
		int other = 0;
		String str = "ABCDE123456abcd!@#$";
		char[] arr = str.toCharArray(); // 将字符串转换成字符数组
		for (int i = 0; i < arr.length; i++) { // 遍历数组
			char ch = arr[i];
			if (ch >= 'A' && ch <= 'Z') {
				big++;
			} else if (ch >= 'a' && ch <= 'z') {
				small++;
			} else if (ch >= '0' && ch <= '9') {
				num++;
			} else {
				other++;
			}
		}

		System.out.println("这个字符串中,大写字母有" + big + "个,小写字母有" + small + ",数字有"
				+ num + ",其他字符" + other);
	}

	public static void demo2() { // 将字符串转换成字符数组
		String str = "你好你好";
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void demo1() { // 将字符数组转字符串
		char[] arr = { 'a', 'b', 'c', 'd', 'e' };
		String str = new String(arr);
//		String str = new String(arr, 1, 3); // 截取部分
		System.out.println(str);
	}

}

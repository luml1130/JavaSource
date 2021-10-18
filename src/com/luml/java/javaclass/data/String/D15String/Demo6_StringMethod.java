package com.luml.java.javaclass.data.String.D15String;
/*
 * 3,转换
		3.1 byte[] getBytes();					//编码,让计算机看的懂的,用默认的编码表,将字符串转换成字节数组
			byte[] getBytes(String)				//用指定的编码表进行编码
		3.2 char[] toCharArray();				//将字符串转换成字符数组
		3.3 static String copyValueOf(char[]);	//将字符数组转换成字符串
		    static String copyValueOf(char[] data, int offset, int count);//将字符数组转换字符串,通过offset开始,截取count个
		3.4 static String valueOf(char[]);		//将字符数组转换成字符串
		    static String valueOf(char[] data, int offset, int count);//将字符数组转换字符串,通过offset开始,截取count个
		3.5 static String valueOf(int);			//将一个int数转换成字符串
			static String valueOf(double);
			static String valueOf(boolean);
				...
			
		3.6 static String valueOf(object);		
			和object.toString():结果是一样的。
		3.7 String toLowerCase():				//将字符串全部转换为小写
			String toUpperCase():				//将字符串全班转换为大写
		3.8 "abc".concat("kk");					//将两个字符串相连接,产生新的字符串
 */
public class Demo6_StringMethod {
	public static void main(String[] args) {
//		demo1();
//		demo2();
//		demo3();
//		demo4();
		demo5();
		/*
		 * String str = "lkajfdljajalkfdjlsajAflkBajfdClkjaDdslkjf";将第一字母变成大写,其他所有字母小写
		 */
		
		String str = "lkajfdljajalkfdjlsajAflkBajfdClkjaDdslkjf";
		//链式编程,特点是每次调用方法返回的都是对象,这样就可以继续调用方法
		String newStr = str.substring(0, 1).toUpperCase().concat(str.substring(1).toLowerCase());
		System.out.println(newStr);
	}

	public static void demo5() {
		String s1 = "itcast";
		String s2 = "heima";
		System.out.println(s1.concat(s2));
		System.out.println(s1 + s2);
	}

	public static void demo4() {
		String s1 = "aBcDe";
		System.out.println(s1.toLowerCase());
		System.out.println(s1.toUpperCase());
	}

	public static void demo3() {
		Demo d = new Demo();
		String str1 = String.valueOf(d);
		System.out.println(str1);
		String str2 = d.toString();
		System.out.println(str2);
	}

	public static void demo2() {
		char[] arr = {'a','b','c','d','e'};
		String str = String.valueOf(arr);
		String str2 =String.valueOf(10);
		System.out.println(str2);
	}

	public static void demo1() {
		char[] arr = {'a','b','c','d','e'};
//		String str = String.copyValueOf(arr);
		String str = String.copyValueOf(arr, 1, 3);
		System.out.println(str);
	}
}

class Demo{
	
}

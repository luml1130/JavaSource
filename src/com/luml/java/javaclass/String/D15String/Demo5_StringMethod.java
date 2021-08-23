package com.luml.java.javaclass.String.D15String;
/*
 * 2,获取
		2.1 int length();						//获取字符串的长度
		2.2 char charAt(index);					//通过索引获取对应的字符
		2.3 int indexOf(int ch);				//通过传入int数或者是字符找对应索引
			int indexOf(int ch,fromIndex);		//在指定fromIndex的位置查找传入的字符
		2.4 int indexOf(string str);			//通过传入字符串查找字符串所对应的索引
		    int idnexOf(string str,fromIndex);	//通过指定fromIndex的位置查找传入的字符串
		2.5 int lastIndexOf(ch);				//通过传入的字符从后向前找字符的索引值,把从后向前第一次找到的索引值返回
			int lastIndexOf(ch,fromIndex):		//通过指定fromIndex的位置,从后向前查找字符,把从后向前第一次找到的索引值返回
		2.6 int lastIndexOf(string);			//通过传入的字符串,从后向前查找,将第一次找到字符串中第一个字符的索引返回
			int lastIndexOf(string,fromIndex):	//通过指定fromIndex的位置,从后向前查找对应字符串,将第一次找到字符串中第一个字符的索引返回
		2.7 String substring(start);			//通过传入的索引值开始向后截取,截取的是索引到length
			String substring(start,end);		//通过传入的两个索引值截取,有开始有结尾,包含头不包含尾
 */
public class Demo5_StringMethod {
	public static void main(String[] args) {
		//demo1();
//		demo2();
//		demo3();
//		demo4();
//		demo5();
		demo6();
		String s1 = "abcitcastabc";
		String s2 = s1.substring(3);
		String s3 = s1.substring(3, 6);			//包含头不包含尾,左闭右开
		System.out.println(s2);
		System.out.println(s3);
	}

	public static void demo6() {
		String str = "abcitcastabc";
//		int index = str.indexOf("abc");
		int index2 = str.lastIndexOf("abc",5);	//找这字符串的第一个字符的索引
		System.out.println(index2);
	}

	public static void demo5() {
		String str = "abcitcastabc";
		int index = str.lastIndexOf('c', 5);	//从指定位置开始向前找,包含指定位置
		System.out.println(index);
	}

	public static void demo4() {
		String str = "abcitcastabc";
		int index = str.lastIndexOf('c');
		System.out.println(index);
	}

	public static void demo3() {
		String str = "abcitcastabc";
		int index1 = str.indexOf('c',7);		//从指定的位置开始,向后找,包含指定位置
		System.out.println(index1);
	}

	public static void demo2() {
		String str = "ab传智播客cde";
		int index = str.indexOf('a');			//找到就返回对应的索引值,找不到就返回-1
		System.out.println(index);
	}

	public static void demo1() {
		String str = "ab传智播客cde";
		//System.out.println(str.length());
		char ch = str.charAt(10);			//角标越界
		System.out.println(ch);
	}
}

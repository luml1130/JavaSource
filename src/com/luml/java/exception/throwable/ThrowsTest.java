package com.luml.javase.exception.throwable;

import java.io.UnsupportedEncodingException;

public class ThrowsTest {

	public static void main(String[] args) {
		ThrowsTest t = new ThrowsTest();
		/*try {
			int result = t.translate("123l");
			System.out.println(result);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		System.out.println("main");*/
		
		try {
			t.test("中国");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//方法向上抛异常，抛给调用者
	public int translate(String str) throws NumberFormatException, StringIndexOutOfBoundsException {
		return Integer.parseInt(str);
	}
	
	public void test(String str) throws UnsupportedEncodingException {
		
			String strTemp = new String(str.getBytes("gb2312"), "ISO-8859-1");
			System.out.println(strTemp);
			String strResult = new String(strTemp.getBytes("ISO-8859-1"), "gb2312");
			System.out.println(strResult);
	}
}

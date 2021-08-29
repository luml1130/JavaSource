package com.luml.javase.exception.throwable;

public class ThrowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThrowTest t = new ThrowTest();
		try {
			t.divide(new int[]{1, 3});
		} catch (MyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("aaaa中国".getBytes().length);
	}

	public void divide(int[] arr) throws MyException {
		try {
			System.out.println(arr[2]/arr[1]);
			System.out.println("try");
		} catch(ArithmeticException e) {
			//手抛自定义异常
			throw new MyException("除数不能为0");
		} catch (Exception e) {
			throw new MyException("数组下标越界");
		}
	}
}

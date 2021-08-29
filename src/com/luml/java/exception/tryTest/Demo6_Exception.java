package com.luml.javase.exception.tryTest;

public class Demo6_Exception {

	/**
	 * Return 在将死之前看看有没有finally 让finally执行
	 * 看看我执行了吗
	 * finally执行了吗
	 * @param args
	 */
	public static void main(String[] args) {
		Div3 d = new Div3();
		try {
			int x = d.div(3, 0);
			System.out.println(x);
		} catch (Exception e) {
			System.out.println("看看我执行了吗");
			return;
//			System.exit(0);
		} finally {
			System.out.println("finally执行了吗");
		}
	}

}

class Div3 {
	public int div(int a,int b){
		return a / b;				
	}
}


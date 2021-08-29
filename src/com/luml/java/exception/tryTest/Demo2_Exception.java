package com.luml.java.exception.tryTest;

public class Demo2_Exception {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Div d = new Div();
		try {							//自己处理异常
			int x = d.div(10, 0);
			System.out.println(x);
		} catch (Exception e) {			//Exception e = new ArithmeticException("by zero");
			//System.out.println("除数为零啊,不能这样");
			//System.out.println(e.getMessage()); 	//异常信息
			//System.out.println(e.toString()); 	//异常类名 + 异常信息
			//e.printStackTrace();					//异常类名 + 异常信息 + 错误的行号
//			throw e;
			throw new ArithmeticException("By zero");
		}
		
		System.out.println("11111111111111111111111");
	}

}

class Div {
	public int div(int a,int b) {
		return a / b;				//new ArithmeticException("by zero");
	}
}

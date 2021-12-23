package com.luml.javase.exception.throwable;

public class TryTest {
	public static void main(String[] args) {
		/*int a = 12;
		int b = 0;*/
		int[] arr = {12, 0};
		try {
			/*
			 * 正常执行的代码
			 * try中一旦发生异常，try里以后的代码都不能执行了
			 * 会直接跳到相应的catch中执行
			 */
			System.out.println(arr[2]/arr[1]);
			System.out.println("try");
			//能捕获的异常类型
		} catch(ArithmeticException e) {
			//如果发生异常的处理代码
			System.out.println("除数不能为0");
			//执行完finally再return
			return;
			/*
			 * 父异常类型可以处理子异常的对象
			 * 因此，多catch时父异常一定要写子异常后面
			 */
		} catch (Exception e) {
			//System.out.println("数组下标越界");
			e.printStackTrace();
			//退出应用程序，finally不执行
			System.exit(0);
			/*
			 * 最后执行的块儿，有不有异常都执行
			 * 一般重要资源的关闭，在finally中
			 */
		} finally {
			System.out.println("finally");
		}
		//程序处理完异常后，会继续往下执行
		System.out.println("main"); 
	}
}

package com.luml.java.exception.customer;

import java.io.FileNotFoundException;


public class exceptionTest {
	public static void main(String[] args) throws FileNotFoundException {//throws Exception
		try{
			Demo demo = new Demo();
			/*int div = demo.div(4, 0);
			System.out.println(div);*/
		}catch(Exception e){
			System.out.println(4444);
			/*System.out.println(e.toString());//java.lang.ArithmeticException: / by zero
			System.out.println(e.getMessage());/// by zero
			System.out.println(e.getStackTrace());//[Ljava.lang.StackTraceElement;@47004b78
			e.printStackTrace();//java.lang.ArithmeticException: / by zero
								//at test.Demo.div(exceptionTest.java:21)
								//at test.exceptionTest.main(exceptionTest.java:7)*/
			/*File file = new File("D:\\1.log");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("http://www.jb51.net");// 往文件里写入字符串
            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
*/		
			//自定义异常
			AppException n=new AppException();
			throw n;
//			new AppException("sfsdfs");
		}finally{
			System.out.println(5555);
		}
	
	}

}
class Demo{
	public int div(int a,int b){
		return a/b;
	}
}

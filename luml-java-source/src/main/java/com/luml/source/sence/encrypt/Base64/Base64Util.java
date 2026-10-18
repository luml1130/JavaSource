package com.luml.source.sence.encrypt.Base64;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

/**
 * 爱学堂
 * 使用sun.misc.BASE64Encoder()加密
 * 和sun.misc.BASE64Decoder()解密
 * @author huashuo
 */
public class Base64Util {
	
	public static void main(String[] args) throws IOException {
		/* Base64Util bs = new Base64Util();
		 String s = "abcd";
	     System.out.println("加密前：" + s);
	     String x = bs.getBASE64(s.getBytes());
	     System.out.println("加密后：" + x);
	     String x1 = new String(getFromBASE64(x));
	     System.out.println("解密后：" + x1);*/
	     
	     String simple = simple();
	     System.out.println(simple);
	}
	
	//加密和解密简单
	public static String simple() throws IOException{
		System.out.print("请输入用户名:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String userName = in.readLine();
		System.out.print("请输入密码:");
		String password = in.readLine();
		/*BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("编码后的用户名为:"+ encoder.encode(userName.getBytes()));
		System.out.println("编码后的密码为:" + encoder.encode(password.getBytes()));*/
		//BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("编码后的用户名为:"+ Base64.getEncoder().encodeToString(userName.getBytes()));
		System.out.println("编码后的密码为:" + Base64.getEncoder().encodeToString(password.getBytes()));
		return password;	
	}
	
	//加密
	/*public String getBASE64(byte[] b) {
		  String s = null;
		  if (b != null) {
			  s = new BASE64Encoder().encode(b);
		  }
		  return s;
	}*/
	
	//解密
	public static byte[] getFromBASE64(String s){
		byte[] b=null;
		if(s!=null){
			java.util.Base64.Decoder decoder=java.util.Base64.getDecoder();
			try{
				b=decoder.decode(s);
				return b;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return b;
	}
}

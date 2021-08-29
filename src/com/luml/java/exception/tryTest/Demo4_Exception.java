package com.luml.java.exception.tryTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo4_Exception {

	/**
	 * @param args
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args)  {
		try {
			FileInputStream fis = new FileInputStream("F:\\Demo1.java");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	 * Exception和RuntimeException的区别
	 * 1,Exception在编译时,必须对其处理,如果不处理,编译不通过
	 *   RuntimeException在编译是,可以处理,也可以不处理
	 * 2,Exception更像是未雨绸缪,在要书写代码时,可能会与硬盘服务器等进行交互,在交互的过程可能会出现这样那样的异常,那么必须处理
	 *   RuntimeException如果出现,需要程序员对代码进行修正
	 */

}

package com.luml.thread.study;
/**
 * @author luml
 */
public class Demo1_Thread {
	public static void main(String[] args) {	//通过垃圾回收机制证明jvm是多线程的
		for (int i = 0; i < 100000; i++) {
			new Demo();
		}		
		for (int i = 0; i < 1000; i++) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
		}
	}
}

class Demo {
	@Override
	public void finalize(){
		System.out.println("垃圾被回收了");
	}	
}

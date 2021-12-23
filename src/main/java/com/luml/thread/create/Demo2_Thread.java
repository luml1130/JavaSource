package com.luml.thread.create;

public class Demo2_Thread {

	public static void main(String[] args) {
		MyThread mt = new MyThread();
		mt.start();
		
		for(int i = 0; i < 1000;i++) {
			System.out.println("aaaaaaaaaaaaaaaaaaa");
		}
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("bb");
		}
	}
}


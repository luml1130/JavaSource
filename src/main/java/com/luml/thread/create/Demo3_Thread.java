package com.luml.thread.create;

public class Demo3_Thread {

	public static void main(String[] args) {
		MyRunnable mr = new MyRunnable();
		Thread t = new Thread(mr);
		t.start();
		for(int i = 0; i < 1000;i++) {
			System.out.println("aaaaaaaaaaaaaaaaaaa");
		}
	}

}

class MyRunnable implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("bb");
		}
	}	
}

package com.luml.thread.method;

public class SleetDemo6 {

	public static void main(String[] args) throws InterruptedException {

		for(int x = 1000; x >= 1; x--) {
			Thread.sleep(1000);
			System.out.println("   " + x);
		}

	}

}

package com.luml.thread.study;

public class Demo5_Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			public void run() {
				for(int i = 0; i < 10;i++) {
					System.out.println("aaaaaaaaaaaaaaaaaaa" + "............" + getName());
				}
			}
		};
		t1.setName("周xx");							//设置名字
		t1.start();
		
		Thread t2 = Thread.currentThread();
		t2.setName("中国油王");
		System.out.println(t2.getName());
	}

}

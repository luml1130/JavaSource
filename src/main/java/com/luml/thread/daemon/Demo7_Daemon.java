package com.luml.thread.daemon;

public class Demo7_Daemon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(){									
			public void run() {
				for (int i = 0; i < 2; i++) {		
					System.out.println("bb" +"........." +getName());
				}
			}
		};
		
		Thread t2 = new Thread(){									
			public void run() {
				for (int i = 0; i < 10; i++) {		
					System.out.println("aaaaaaaaaaaaaaaaa" +"........." +getName());
				}
			}
		};
		//将t2设置守护线程
		t2.setDaemon(true);
		t1.start();
		t2.start();
	}

}

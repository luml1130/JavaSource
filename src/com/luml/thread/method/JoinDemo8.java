package com.luml.thread.method;

public class JoinDemo8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Thread t1 = new Thread(){									
			public void run() {
			for (int i = 0; i < 20; i++) {
				System.out.println("bb" +"........." +getName());
			}
			}
		};
		
		Thread t2 = new Thread(){									
			public void run() {
			for (int i = 0; i < 20; i++) {
				if(i == 2) {
					try {
						//t1.join();//这个相当于插队等aaa执行完在bb
						t1.join(10);//相当于t1线程插队了10毫秒
					} catch (InterruptedException e) {
						e.printStackTrace();//因为父类没有抛异常
					}
				}
				System.out.println("aaaaaaaaaaaaaaaaa" +"........." +getName());
			}
			}
		};
		t1.start();
		t2.start();
	}

}

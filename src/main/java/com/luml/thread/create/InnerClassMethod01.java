package com.luml.thread.create;
/*
 * 匿名内部类对两种方式的优化
 */
public class InnerClassMethod01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(){									//继承Thread类
			public void run() {
				for (int i = 0; i < 1000; i++) {		
					System.out.println("bb" +"........." +getName());
				}
			}
		}.start();
		
		new Thread(){									//继承Thread类
			public void run() {
				for (int i = 0; i < 1000; i++) {		
					System.out.println("ccccccccccc" +"........." +getName());
				}
			}
		}.start();
		
		new Thread(new Runnable(){						//实现Runnable接口
			@Override
			public void run() {
				for(int i = 0; i < 1000;i++) {
					System.out.println("aaaaaaaaaaaaaaaaaaa" +"---------" + Thread.currentThread().getName());
				}
			}
		}).start();
		
		Thread t = Thread.currentThread();				//获取Thread对象的引用
		System.out.println(t.getName());
	}

}

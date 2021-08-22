package com.luml.threadPool.study;

public class ThreadPoolTest {
	public static void main(String[] args) {
		//MyRunnable myrunnable=new MyRunnable();
		//Thread t = new Thread(myrunnable);
		//t.start();
		Task task = new Task() {
			@Override
			protected boolean useDb() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public Task[] taskCore() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			protected boolean needExecuteImmediate() {
				// TODO Auto-generated method stub
				return false;
			}
			public String info() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		ThreadPool instance = ThreadPool.getInstance();
		System.out.println(instance.getInfo());
		
		instance.addTask(task);
		
	}
}


package com.luml.java.javaclass.studyD10;

class Demo20_Template {
	public static void main(String[] args) {
		Demo d = new Demo();
		d.getTime();
	}
}

abstract class GetTime {
	public final void getTime() {						//固定的方法,不让子类去修改的
		long start = System.currentTimeMillis();		//1秒 = 1000毫秒
		code();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public abstract void code();						//不固定的方法,让子类去重写的
}

class Demo extends GetTime {
	@Override
	public void code() {
		int x = 0;
		while(x < 10000) {
			System.out.print("*");
			x++;
		}
	}
}

package book.MultiThreadProgram.Part02.atomicIntergerNoSafe;

import java.util.concurrent.atomic.AtomicLong;

public class MyService {

	public static AtomicLong aiRef = new AtomicLong();

	synchronized public void addNum() {
		System.out.println(Thread.currentThread().getName() + "����100֮���ֵ��:"
				+ aiRef.addAndGet(100));
		aiRef.addAndGet(1);
	}

}

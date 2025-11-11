package book.MultiThreadProgram.Part02.chapter02.synchronizedBlockLockAll.test3;

public class MyObject {
	public void speedPrintString() {
		synchronized (this) {
			System.out.println("speedPrintString ____getLock time="
					+ System.currentTimeMillis() + " run ThreadName="
					+ Thread.currentThread().getName());
			System.out.println("-----------------");
			System.out.println("speedPrintString releaseLock time="
					+ System.currentTimeMillis() + " run ThreadName="
					+ Thread.currentThread().getName());
		}
	}
}

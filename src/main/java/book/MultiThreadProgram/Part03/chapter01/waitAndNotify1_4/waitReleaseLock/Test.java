package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.waitReleaseLock;


public class Test {

	public static void main(String[] args) {

		Object lock = new Object();

		ThreadA a = new ThreadA(lock);
		a.start();

		ThreadB b = new ThreadB(lock);
		b.start();

	}

}

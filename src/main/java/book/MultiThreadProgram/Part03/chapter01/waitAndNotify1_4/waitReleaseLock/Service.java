package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.waitReleaseLock;

public class Service {

	public void testMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin wait()");
				lock.wait();
				System.out.println("  end wait()");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

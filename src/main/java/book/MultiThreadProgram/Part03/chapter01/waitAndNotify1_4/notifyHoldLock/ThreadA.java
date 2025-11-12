package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.notifyHoldLock;


public class ThreadA extends Thread {
	private Object lock;

	public ThreadA(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		Service service = new Service();
		service.testMethod(lock);
	}

}

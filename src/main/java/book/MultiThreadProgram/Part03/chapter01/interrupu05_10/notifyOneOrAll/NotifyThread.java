package book.MultiThreadProgram.Part03.chapter01.interrupu05_10.notifyOneOrAll;


public class NotifyThread extends Thread {
	private Object lock;

	public NotifyThread(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
			lock.notify();
		}
		/*synchronized (lock) {
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
			lock.notifyAll();
		}*/


	}

}

package book.MultiThreadProgram.Part03.chapter01.interrupu05_10.waitInterruptException;

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
			System.out.println("出现异常了，因为呈wait状态的线程被interrupt了！");
		}
	}

}

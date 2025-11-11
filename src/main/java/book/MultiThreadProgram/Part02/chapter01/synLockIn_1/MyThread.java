package book.MultiThreadProgram.Part02.chapter01.synLockIn_1;

public class MyThread extends Thread {
	@Override
	public void run() {
		Service service = new Service();
		service.service1();
	}

}

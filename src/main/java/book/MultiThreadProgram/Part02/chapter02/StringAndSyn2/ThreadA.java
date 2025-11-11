package book.MultiThreadProgram.Part02.chapter02.StringAndSyn2;


public class ThreadA extends Thread {
	private Service service;

	public ThreadA(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.print(new Object());
	}
}

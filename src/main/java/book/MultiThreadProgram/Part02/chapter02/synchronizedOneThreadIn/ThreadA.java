package book.MultiThreadProgram.Part02.chapter02.synchronizedOneThreadIn;


public class ThreadA extends Thread {

	private ObjectService service;

	public ThreadA(ObjectService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		super.run();
		service.serviceMethod();
	}

}

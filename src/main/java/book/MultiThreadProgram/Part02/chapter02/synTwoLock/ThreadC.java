package book.MultiThreadProgram.Part02.chapter02.synTwoLock;


public class ThreadC extends Thread {

	private Service service;

	public ThreadC(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.printC();
	}
}

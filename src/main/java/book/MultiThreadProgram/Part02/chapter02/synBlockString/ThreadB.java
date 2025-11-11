package book.MultiThreadProgram.Part02.chapter02.synBlockString;


public class ThreadB extends Thread {

	private Service service;

	public ThreadB(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.setUsernamePassword("b", "bb");

	}

}

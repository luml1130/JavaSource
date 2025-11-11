package book.MultiThreadProgram.Part02.chapter01.t3;


public class ThreadA extends Thread {

	private PublicVar publicVar;

	public ThreadA(PublicVar publicVar) {
		super();
		this.publicVar = publicVar;
	}

	@Override
	public void run() {
		super.run();
		publicVar.setValue("B", "BB");
	}
}

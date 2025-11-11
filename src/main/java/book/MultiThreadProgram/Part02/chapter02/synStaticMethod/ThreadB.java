package book.MultiThreadProgram.Part02.chapter02.synStaticMethod;


public class ThreadB extends Thread {
	@Override
	public void run() {
		Service.printB();
	}
}

package book.MultiThreadProgram.Part02.chapter02.synStaticMethod;


public class ThreadA extends Thread {
	@Override
	public void run() {
		Service.printA();
	}

}

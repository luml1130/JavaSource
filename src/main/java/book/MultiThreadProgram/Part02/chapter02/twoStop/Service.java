package book.MultiThreadProgram.Part02.chapter02.twoStop;

public class Service {
	synchronized public void methodA() {
		System.out.println("methodA begin");
		boolean isContinueRun = true;
		while (isContinueRun) {
		}
		System.out.println("methodA end");
	}

	synchronized public void methodB() {
		System.out.println("methodB begin");
		System.out.println("methodB end");
	}
}

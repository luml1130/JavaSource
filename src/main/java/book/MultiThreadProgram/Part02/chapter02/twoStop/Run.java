package book.MultiThreadProgram.Part02.chapter02.twoStop;


public class Run {

	public static void main(String[] args) {
		Service service = new Service();

		ThreadA athread = new ThreadA(service);
		athread.start();

		ThreadB bthread = new ThreadB(service);
		bthread.start();
	}

}

package book.MultiThreadProgram.Part01.t.t1;


public class Run {

	public static void main(String[] args) {
		MyThread mythread = new MyThread();
		mythread.start();
		System.out.println("���н�����");
	}

}

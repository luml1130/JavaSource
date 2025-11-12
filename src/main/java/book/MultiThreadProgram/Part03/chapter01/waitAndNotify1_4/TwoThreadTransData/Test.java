package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.TwoThreadTransData;


public class Test {

	public static void main(String[] args) {
		MyList service = new MyList();

		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();

	}

}

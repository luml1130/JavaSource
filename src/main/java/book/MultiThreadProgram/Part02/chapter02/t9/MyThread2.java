package book.MultiThreadProgram.Part02.chapter02.t9;

public class MyThread2 extends Thread {

	private MyOneList list;

	public MyThread2(MyOneList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "B");
	}

}
package book.MultiThreadProgram.Part02.chapter02.syn_Out_asyn;


public class MyThreadB extends Thread {

	private MyList list;

	public MyThreadB(MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			list.add("threadB" + (i + 1));
		}
	}
}

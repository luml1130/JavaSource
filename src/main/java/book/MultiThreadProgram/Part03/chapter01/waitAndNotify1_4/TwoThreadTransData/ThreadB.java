package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.TwoThreadTransData;


public class ThreadB extends Thread {

	private MyList list;

	public ThreadB(MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (list.size() == 5) {
					System.out.println("==5，线程b要退出了！");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

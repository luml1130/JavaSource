package book.MultiThreadProgram.Part03.chapter01.interrupu05_10.waitOld;

//加法
public class Add {

	private String lock;

	public Add(String lock) {
		super();
		this.lock = lock;
	}

	public void add() {
		synchronized (lock) {
			ValueObject.list.add("anyString");
			lock.notifyAll();
		}
	}

}

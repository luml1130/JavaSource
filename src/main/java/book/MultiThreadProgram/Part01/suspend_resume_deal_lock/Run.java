package book.MultiThreadProgram.Part01.suspend_resume_deal_lock;


public class Run {

	public static void main(String[] args) {
		try {
			final SynchronizedObject object = new SynchronizedObject();

			Thread thread1 = new Thread() {
				@Override
				public void run() {
					object.printString();
				}
			};

			thread1.setName("a");
			thread1.start();

			Thread.sleep(1000);

			Thread thread2 = new Thread() {
				@Override
				public void run() {
					System.out
							.println("thread2�����ˣ������벻��printString()������ֻ��ӡ1��begin");
					System.out
							.println("��ΪprintString()������a�߳�����������Զ��suspend��ͣ�ˣ�");
					object.printString();
				}
			};
			thread2.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

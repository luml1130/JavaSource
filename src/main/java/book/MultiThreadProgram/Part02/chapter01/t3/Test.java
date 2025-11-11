package book.MultiThreadProgram.Part02.chapter01.t3;


public class Test {

	public static void main(String[] args) {
		try {
			PublicVar publicVarRef = new PublicVar();
			ThreadA thread = new ThreadA(publicVarRef);
			thread.start();

			Thread.sleep(200);// ��ӡ����ܴ�ֵ��СӰ��

			publicVarRef.getValue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package book.MultiThreadProgram.Part02.chapter03.t10;


public class Run {

	public static void main(String[] args) {
		PrintString printStringService = new PrintString();
		new Thread(printStringService).start();

		System.out.println("我要停止它！stopThread="
				+ Thread.currentThread().getName());
		printStringService.setContinuePrint(false);
	}

}

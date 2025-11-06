package book.MultiThreadProgram.Part01.t.t4_threadsafe;


public class ALogin extends Thread {
	@Override
	public void run() {
		LoginServlet.doPost("a", "aa");
	}
}

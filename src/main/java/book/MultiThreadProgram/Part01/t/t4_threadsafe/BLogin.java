package book.MultiThreadProgram.Part01.t.t4_threadsafe;


public class BLogin extends Thread {
	@Override
	public void run() {
		LoginServlet.doPost("b", "bb");
	}
}

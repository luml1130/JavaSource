package book.MultiThreadProgram.Part01.t.t4_threadsafe;

//本类模拟一个Servlet组件
public class LoginServlet {

	private static String usernameRef;
	private static String passwordRef;

	synchronized public static void doPost(String username, String password) {
		try {
			usernameRef = username;
			if (username.equals("a")) {
				Thread.sleep(5000);
			}
			passwordRef = password;

			System.out.println("username=" + usernameRef + " password="
					+ password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

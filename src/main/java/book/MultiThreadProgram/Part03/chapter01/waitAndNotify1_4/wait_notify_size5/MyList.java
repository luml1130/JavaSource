package book.MultiThreadProgram.Part03.chapter01.waitAndNotify1_4.wait_notify_size5;

import java.util.ArrayList;
import java.util.List;

public class MyList {

	private static List list = new ArrayList();

	public static void add() {
		list.add("anyString");
	}

	public static int size() {
		return list.size();
	}

}

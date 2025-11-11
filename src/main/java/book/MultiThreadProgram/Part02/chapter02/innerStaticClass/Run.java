package book.MultiThreadProgram.Part02.chapter02.innerStaticClass;


public class Run {

	public static void main(String[] args) {

		PublicClass publicClass = new PublicClass();
		publicClass.setUsername("usernameValue");
		publicClass.setPassword("passwordValue");

		System.out.println(publicClass.getUsername() + " "
				+ publicClass.getPassword());

		PublicClass.PrivateClass privateClass = new PublicClass.PrivateClass();
		privateClass.setAge("ageValue");
		privateClass.setAddress("addressValue");

		System.out.println(privateClass.getAge() + " "
				+ privateClass.getAddress());

	}

}

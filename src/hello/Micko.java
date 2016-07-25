package hello;

public class Micko implements hello.LocalModule {

	@Override
	public void testAdd() {
		System.out.println("Ovo je metoda Micko klase iz projekta - ne iz browsera.");
		System.out.println("------------------------ MickoClass - ClassLoader");
		System.out.println(hello.LocalModule.class.getClassLoader());
		System.out.println(hello.Micko.class.getClassLoader());
		System.out.println("------------------------ MickoClass - ClassLoader");
	}

}

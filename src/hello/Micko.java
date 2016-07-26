package hello;

public class Micko implements hello.LocalModule {

	@Override
	public void testAdd() {
		System.out.println("------------------------------------------------- testAdd() metoda iz Micko klase.");
		System.out.println("------------------------ MickoClass - ClassLoader");
		System.out.println(hello.LocalModule.class.getClassLoader());
		System.out.println(hello.Micko.class.getClassLoader());
		System.out.println("------------------------ MickoClass - ClassLoader");
	}
	
	@Override
	public void testAdd1() {
		System.out.println("------------------------------------------------- testAdd1() metoda iz Micko klase.");
	}

}

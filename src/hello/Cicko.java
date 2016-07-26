package hello;

public class Cicko extends Micko {
	@Override
	public void testAdd() {
		System.out.println("------------------------------------------------- testAdd() metoda iz Cicko klase.");
		System.out.println("------------------------ CickoClass - ClassLoader");
		System.out.println(hello.LocalModule.class.getClassLoader());
		System.out.println(hello.Micko.class.getClassLoader());
		System.out.println("------------------------ CickoClass - ClassLoader");
	} 
	

}

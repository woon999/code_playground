package ch13.inheritancerule;

interface A{
	default void hello(){
		System.out.println("Hello from A");
	}
}

interface B extends A{
	default void hello(){
		System.out.println("Hello from B");
	}
}

public class C implements B, A{
	public static void main(String[] args) {
		new C().hello(); // Hello from B
	}
}

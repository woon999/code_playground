package ch13;

interface AA {
	default void hello(){
		System.out.println("Hello from A");
	}
}

interface BB extends AA{}
interface CC extends AA{
}

public class Diamond implements BB, CC{
	public static void main(String[] args) {
		Diamond diamond = new Diamond();
		diamond.hello(); // Hello from A
	}
}

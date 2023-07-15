package jvm.gc;

/**
 * Garbage Collection Memory Leak이 발생하는 경우
 * - Reachable but not live 객체
 *
 */
public class Main {
	public static void main(String[] args) {
		// leakExecution(); // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		notLeakExecution(); // Success
	}

	static void notLeakExecution(){
		NotLeak nlk = new NotLeak();
		for(int a = 0; a<90_000_000; a++){
			nlk.addList(a);
			nlk.removeStr(a);
		}
		System.out.println("Success");
	}

	static void leakExecution(){
		Leak lk = new Leak();
		for(int a = 0; a<90_000_000; a++){
			lk.addList(a);
			lk.removeStr(a);
		}
		System.out.println("Success");
	}
}

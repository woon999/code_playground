package generalfactorial;

public class Main {
	static int iterativeFactorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	static int recursiveFactorial(int n) {
		if (n == 1)
			return 1;
		return n * recursiveFactorial(n - 1);
	}

	static int tailFactorialFunc(int n, int acc) {
		if (n == 1)
			return acc;
		return tailFactorialFunc(n - 1, acc * n);
	}

	static int tailFactorial(int n) {
		return tailFactorialFunc(n, 1);
	}

	public static void main(String[] args) {
		int iterResult = iterativeFactorial(10);
		int recurResult = recursiveFactorial(10);
		int tailResult = tailFactorial(10);
		System.out.println("iterResult = " + iterResult);
		System.out.println("recurResult = " + recurResult);
		System.out.println("tailResult = " + tailResult);

		try{
			System.out.println(tailFactorial(20000));
		}catch (VirtualMachineError ex){
			System.out.println(ex);
		}
	}
}

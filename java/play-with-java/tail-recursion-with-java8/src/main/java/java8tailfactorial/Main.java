package java8tailfactorial;

import static java8tailfactorial.FactorialToBigInteger.*;

import java.math.BigInteger;
import java.util.stream.Stream;


@FunctionalInterface
interface TailCall<T> {
	TailCall<T> apply();

	default boolean isComplete() {
		return false;
	}

	default T result() {
		throw new Error("not implemeted");
	}

	default T invoke() {
		return Stream.iterate(this, TailCall::apply)
			.filter(TailCall::isComplete)
			.findFirst()
			.get()
			.result();
	}
}

class TailCalls {
	public static <T> TailCall<T> call(final TailCall<T> nextCall) {
		return nextCall;
	}

	public static <T> TailCall<T> done(final T value) {
		return new TailCall<T>() {
			@Override public boolean isComplete() { return true; }
			@Override public T result() { return value; }
			@Override public TailCall<T> apply() {
				throw new Error("not implemented");
			}
		};
	}
}

class FactorialToInt {
	private static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
		if (number == 1) {
			return TailCalls.done(factorial);
		}
		return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
	}

	public static int factorial(final int number){
		return factorialTailRec(1, number).invoke();
	}
}

class FactorialToBigInteger {
	final static BigInteger ONE = BigInteger.ONE;
	final static BigInteger TEN = new BigInteger("10");
	final static BigInteger TWENTY_K = new BigInteger("20000");

	private static BigInteger decrement(final BigInteger number){
		return number.subtract(ONE);
	}

	private static BigInteger multiply(final BigInteger first, final BigInteger second){
		return first.multiply(second);
	}

	private static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number) {
		if (number.equals(ONE)) {
			return TailCalls.done(factorial);
		}
		return TailCalls.call(() -> factorialTailRec(multiply(factorial, number), decrement(number)));
	}

	public static BigInteger factorial(final BigInteger number){
		return factorialTailRec(ONE, number).invoke();
	}

}


public class Main {
	public static void main(String[] args) {
		int tailResult =  FactorialToInt.factorial(10);
		System.out.println("tailResult = " + tailResult);

		BigInteger tailResult2 = FactorialToBigInteger.factorial(TEN);
		System.out.println("tailResult2 = " + tailResult2);


		try{
			System.out.println(String.format("%.30s...", FactorialToInt.factorial(20000)));
			System.out.println(String.format("%.30s...", FactorialToBigInteger.factorial(TWENTY_K)));
		}catch (VirtualMachineError ex){
			System.out.println(ex);
		}
	}
}

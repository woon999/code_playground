package ch5.item30.identityfunc;

import java.util.Collection;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class Practice {
	private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

	@SuppressWarnings("unchecked")
	public static <T> UnaryOperator<T> identityFunction() {
		return (UnaryOperator<T>)IDENTITY_FN;
	}

	public static void main(String[] args) {
		String[] strings = {"삼베", "대마", "나일론"};
		UnaryOperator<String> sameString = identityFunction();
		for(String s : strings){
			System.out.println(sameString.apply(s));
		}

		Number[] numbers = {1, 2.0, 3L};
		UnaryOperator<Number> sameNumber = identityFunction();
		for (Number n : numbers) {
			System.out.println(sameNumber.apply(n));
		}

	}
	public static <E extends Comparable<E>> E max(Collection<E> c){
		if(c.isEmpty()){
			throw new IllegalArgumentException("컬렉션이 비었습니다.");
		}

		E result = null;
		for(E e : c){
			if(result == null || e.compareTo(result) > 0){
				result = Objects.requireNonNull(e);
			}
		}
		return result;
	}
}

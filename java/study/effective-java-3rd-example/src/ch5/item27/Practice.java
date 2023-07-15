package ch5.item27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Lark {
}

public class Practice {

	private static int size = 5;
	private static Object[] elements = new Object[size];

	public static void main(String[] args) {
		Set<Lark> exaltation = new HashSet<>();
		elements[0] = 1;
		elements[1] = 2;
		elements[2] = 3;

		Integer[] integers = Practice.toArray(new Integer[] {100, 200, 300, 4, 5, 6});
		Arrays.stream(integers).forEach(i -> System.out.println(i));

	}

	public static <T> T[] toArray(T[] a) {
		if (a.length < size) {
			// 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[]로 같으므로 올바른 형변환이다.
			@SuppressWarnings("unchecked")
			T[] result = (T[])Arrays.copyOf(elements, size, a.getClass());
			return result;
		}
		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

}

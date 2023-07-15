package ch5.item31;

import java.util.Arrays;
import java.util.List;

public class Swap {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3);
		list.stream().forEach(s -> System.out.println(s));
		swap(list,1,2);
		list.stream().forEach(s -> System.out.println(s));
	}

	public static void swap(List<?> list, int i, int j) {
		swapHelper(list, i, j);
	}

		// private 도우미 메서드 (와일드카드 타입 -> 실제 타입)
	private static <E> void swapHelper(List<E> list, int i, int j){
		list.set(i, list.set(j, list.get(i)));
	}

}

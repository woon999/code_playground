package ch5.item32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Practice {
	public static void main(String[] args) {
		// dangerous(List.of("abc")); // ClassCastException
		// String[] attributes = pickTwo("good", "fast", "cheap"); // ClassCastException

		// type safe
		List<String> attributes = pickTwo_typesafe("good", "fast", "cheap");
		System.out.println("attributes = " + attributes);

		// type safe
		List<String> friends = List.of("friend");
		List<String> romans = List.of("abc");
		List<String> countrymen = List.of("zzz");
		List<List<String>> audience = flatten(List.of(friends, romans, countrymen));
		System.out.println("audience = " + audience);
	}

	static void dangerous(List<String>... stringLists){
		List<Integer> intList = List.of(42);
		Object[] objects = stringLists;
		objects[0] = intList; // 힙 오염 발생
		String s = stringLists[0].get(0); // ClassCastException
	}

	// 아이템 28 컴파일 에러 - 제네릭 배열 생성 불가
	static void dangerous2(List<String>[] stringLists){
		List<Integer> intList = List.of(42);
		Object[] objects = stringLists;
		objects[0] = intList; // 힙 오염 발생
		String s = stringLists[0].get(0); // ClassCastException
	}

	static <T> T[] toArray(T... args){
		return args;
	}

	static <T> T[] pickTwo(T a, T b, T c){
		switch (ThreadLocalRandom.current().nextInt(3)){
			case 0: return toArray(a, b);
			case 1: return toArray(a, c);
			case 2: return toArray(b, c);
		}
		throw new AssertionError(); // 도달할 수 없다.
	}

	static <T> List<T> pickTwo_typesafe(T a, T b, T c){
		switch (ThreadLocalRandom.current().nextInt(3)){
			case 0: return List.of(a, b);
			case 1: return List.of(a, c);
			case 2: return List.of(b, c);
		}
		throw new AssertionError(); // 도달할 수 없다.
	}

	@SafeVarargs
	static <T> List<T> flatten(List<? extends T>... lists){
		List<T> result = new ArrayList<>();
		for(List<? extends T> list : lists){
			result.addAll(list);
		}
		return result;
	}

	static <T> List<T> flatten_typesafe(List<List<? extends T>> lists){
		List<T> result = new ArrayList<>();
		for(List<? extends T> list : lists){
			result.addAll(list);
		}
		return result;
	}




}

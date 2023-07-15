package ch4.item15;

import static ch4.item15.Thing.*;

import java.util.Arrays;


public class Main {
	public static void main(String[] args) {
		System.out.println("---- 문제점 ----");
		문제점();
		System.out.println("---- 해결책 1 ----");
		해결책1();
		System.out.println("---- 문제점 ----");
		해결책2();
	}

	private static void 문제점(){
		System.out.println(Arrays.toString(VALUES)); // 1, 2
		VALUES[0] = new Thing(1000);
		System.out.println(Arrays.toString(VALUES)); // 1000, 2
	}

	private static void 해결책1(){
		System.out.println(VALUES2); // 1, 2
		// VALUES2.get(0) = new Thing(999);
		System.out.println(VALUES2); // 1, 2
	}

	private static void 해결책2(){
		System.out.println(Arrays.toString(values())); // 1, 2
		values()[0] = new Thing(999);
		System.out.println(Arrays.toString(values())); // 1, 2
	}
}

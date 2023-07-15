package ch5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Practice {

	public static void main(String[] args) {
	}

	@Test
	public void runtime_fail(){
		Object[] objectArray = new Long[1];
		// ArrayStroeException을 던진다.

	}

	@Test
	public void compile_fail(){
		// List<Object> ol = new ArrayList<Long>(); // 호환되지 않는 타입이다.
		// ol.add("타입이 달라 넣을 수 없다.");
	}

	// 제네릭 배열 생성을 허용하지 않는 이유
	@Test
	public void compile_fail2(){
		// List<String>[] stringsList = new List<Integer>[1]; // 컴파일 에러

		List<String>[] stringsList = new List[1]; // (1)
		List<Integer> intList = List.of(42); // (2)
		Object[] objects = stringsList; // (3)
		objects[0] = intList; // (4)
		// String s = stringsList[0].get(0); // (5)
		Assertions.assertThrows(ClassCastException.class, () -> {String s  = stringsList[0].get(0);});
	}

	class Chooser{
		private final Object[] choiceArray;

		public Chooser(Collection choices){
			choiceArray = choices.toArray();
		}

		public Object choose(){
			Random rnd = ThreadLocalRandom.current();
			return choiceArray[rnd.nextInt(choiceArray.length)];
		}
	}

	@Test
	void choose1_test(){
		Chooser chooser = new Chooser(List.of(1,2,3));
		int choose = (int)chooser.choose(); // 형변환
		System.out.println("choose = " + choose);
	}

}

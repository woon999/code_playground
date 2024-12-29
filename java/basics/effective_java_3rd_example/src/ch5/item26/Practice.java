package ch5.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

enum Stamp{
	STAMP_A, STAMP_B, STAMP_C;
}
enum Coin{
	COIN_10, COIN_50, COIN_100;
}

public class Practice {
	// Stamp 인스턴스만 취급한다.
	private final Collection<Stamp> stamps = new ArrayList<>();

	// 반복자 로 타입 - 따라하지 말것
	@Test
	void test0(){
		// stamps.add(10);
		// stamps.add(Coin.COIN_10);
		stamps.add(Stamp.STAMP_A);
		for(Iterator i = stamps.iterator(); i.hasNext();){
			Stamp stamp = (Stamp) i.next();
			System.out.println("stamp = " + stamp);
		}
	}


	// unSafeAdd가 로 타입 사용 - 런타임 실패
	@Test
	void test1(){
		List<String> strings = new ArrayList<>();
		unSafeAdd(strings, Integer.valueOf(42));
		// String s = strings.get(0); // 컴파일러가 자동으로 형변환을 넣어준다.
		// System.out.println("s = " + s);
	}

	private void unSafeAdd(List list, Object o){
		list.add(o);
	}

	@Test
	void test2(){
		int num = numElementsInCommon(Set.of(1), Set.of(2));
		System.out.println("num = " + num);
	}

	// 잘못된 예 - 모르는 타입의 원소도 받는 로 타입을 사용했다.
	// private int numElementsInCommon(Set s1, Set s2){
	// 비한정적 와일드카드 타입을 사용하라. - 타입 안전하면 유연하다.
	private int numElementsInCommon(Set<?> s1, Set<?> s2){
		int result = 0;
		for(Object o1 : s1){
			if(s2.contains(o1)){
				result++;
			}
		}
		return result;
	}

	// Collection<?>에는 (null 외에는) 어떤 원소도 넣을 수 없다.
	@Test
	void test3(){
		Collection<?> collection = new ArrayList<>();
		// collection.add("abc");
	}

	// 로 타입을 써도 좋은 예 - instanceof 연산자
	@Test
	void test4(){
		Set<?> o = new HashSet<>();
		if(o instanceof Set){ // 로 타입
			Set<?> s = (Set<?>) o; // 와일드카드 타입
			// ..
		}
	}

}

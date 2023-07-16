import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * PECS는 "Producer Extends, Consumer Super"의 약자로,
 * 이는 제네릭을 사용할 때 와일드카드를 효과적으로 사용하기 위한 원칙입니다. 이 원칙에 따르면:
 *
 * "Producer Extends": 만약 컬렉션에서 데이터를 읽어오는 용도로만 사용한다면 (즉, 데이터를 생산(Produce)한다면), extends 와일드카드를 사용해야 한다. (Collection<? extends T> in)
 * "Consumer Super": 만약 컬렉션에 데이터를 쓰는 용도로만 사용한다면 (즉, 데이터를 소비(Consume)한다면), super 와일드카드를 사용해야 한다. (Collection<? super T> out)
 */
public class MyList<T> {
	Object[] element = new Object[5];
	int idx = 0;

	// 생성자(Collection<? extends T> in): 컬렉션에서 데이터를 읽어오는 용도로만 사용. 내부 배열에 추가하여 인스턴스화 하는 생성자.
	public MyList(Collection<? extends T> in) {
		for (T elem : in) {
			element[idx++] = elem;
		}
	}

	// 소비자(Collection<? super T> out): 컬렉션에 데이터를 쓰는 용도로만 사용. 내부 배열의 요소를 모두 매개변수에 추가해주는 메서드.
	public void clone(Collection<? super T> out) {
		for (Object elem : element) {
			out.add((T) elem);
		}
	}

	public static void main(String[] args) {
		// Producer Extends 예제
		List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5);
		MyList<Number> myList = new MyList<>(inputList); // Integer는 Number를 확장합니다. (Integer extends Number)
		for (Object o : myList.element) {
			System.out.println(o);
		}

		// Consumer Super 예제
		MyList<Integer> myList2 = new MyList<>(inputList);
		List<Number> outputList = new ArrayList<>();
		myList2.clone(outputList); // Number는 Integer의 슈퍼클래스입니다. (Number super Integer)
		for (Number n : outputList) {
			System.out.println(n);
		}
	}
}

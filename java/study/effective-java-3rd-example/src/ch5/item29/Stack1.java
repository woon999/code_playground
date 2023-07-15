package ch5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

// 제네릭 배열 오류 해결하기 - 1. 형변환으로 우회하기
public class Stack1<E> {
	private E[] elements;
	private int size;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	// 배열 elements는 push(E)로 넘어온 E 인스턴스만 담는다.
	// 따라서 타입 안정성을 보장하지만,
	// 이 배열의 런타임은 E[]가 아닌 Object[]다!
	@SuppressWarnings("unchecked")
	public Stack1() {
		elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		if (size % 10 == 0) {
			System.out.println("stack push " + size);
		}
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		E result = elements[--size];
		elements[size] = null; // 다 쓴 참조 해제
		return result;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
}

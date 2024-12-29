package ch2.item7.memory_not_leak;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackNotLeak {
	private Object[] elements;
	private int size;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public StackNotLeak() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		if (size % 10 == 0) {
			System.out.println("stack push " + size);
		}
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[--size];
		elements[size] = null; // 다 쓴 참조 해제
		return result;
	}

	/**
	 * 원소를 위한 공간을 적어도 하나 이상 확보한다.
	 * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
	 */
	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
			System.out.println("배열 크기 확장 : " + (size));
		}
	}

	public void printStack(int size) {
		if(elements.length < size ) return;
		for (int i = 0; i < size; i++) {
			System.out.print(elements[i] + " ");
		}
		System.out.println();
	}

}

package ch2.item7.memory_leak;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackLeak {
	private Object[] elements;
	private int size;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public StackLeak() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
		if (size % 10 == 0) {
			System.out.println("stack push " + size);
		}
	}

	// memory leak 발생
	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}

	/**
	 * 원소를 위한 공간을 적어도 하나 이상 확보한다.
	 * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
	 */
	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
			System.out.println("배열 크기 확장 : "  + (size));
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

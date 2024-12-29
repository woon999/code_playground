package ch5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.concurrent.DelayQueue;

// 제네릭 배열 오류 해결하기 - 2. 필드 타입을 Object[]로 변경하기
public class Stack2<E> {
	private Object[] elements;
	private int size;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public Stack2() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		if (size % 10 == 0) {
			System.out.println("stack push " + size);
		}
		elements[size++] = e;
	}

	// 비검사 경고를 숨긴다
	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		// push에서 E 타입만 허용하므로 이 형변환은 안전하다.
		@SuppressWarnings("unchecked")
		E result = (E)elements[--size];
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

	public static void main(String[] args) {
		String[] data = {"a","b","c"};
		Stack2<String> stack = new Stack2<>();
		for(String d : data){
			stack.push(d);
		}
		while(!stack.isEmpty()){
			System.out.println(stack.pop().toUpperCase());
		}
	}
}

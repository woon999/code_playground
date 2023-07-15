package ch5.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

public class Stack<E> {
	private Object[] elements;
	private int size;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public Stack() {
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

	public void popAll(Collection<? super E> dst){
		while(!isEmpty()){
			dst.add(pop());
		}
	}


	public void pushAll(Iterable<? extends E> src){
		for(E e : src){
			push(e);
		}
	}

	public static void main(String[] args) {
		Stack<Number> numberStack = new Stack<>();
		Iterable<Integer> integers = Arrays.asList(1,2,3);
		numberStack.pushAll(integers);
		System.out.println(numberStack.size);

		List<Object> list = new ArrayList<>();
		for(int i=1; i<=3; i++){
			list.add(i);
		}

		Collection<Object> objects = list;
		numberStack.popAll(objects);
		System.out.println(numberStack.size);
	}
}

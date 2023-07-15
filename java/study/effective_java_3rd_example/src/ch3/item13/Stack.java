package ch3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack implements Cloneable{
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[size--];
		elements[size] = null; // 다 쓴 참조 해제
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	public void print(){
		Arrays.stream(elements).forEach(s -> System.out.print(s+" "));
		System.out.println();
	}

	@Override
	public Stack clone() {
		try{
			Stack result = (Stack) super.clone();
			result.elements = elements.clone();
			return result;
		}catch (CloneNotSupportedException e){
			throw new AssertionError();
		}
	}

	public static void main(String[] args) {
		Stack s1 = new Stack();
		for(int i=0; i<10; i++){
			s1.push(i);
		}
		System.out.println("stack hashcode : " + s1.hashCode());
		s1.print();

		Stack clone = s1.clone();
		System.out.println("clone hashcode : " + clone.hashCode());
		clone.print();
	}

}

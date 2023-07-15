package ch2.item7;

import ch2.item7.memory_leak.StackLeak;
import ch2.item7.memory_not_leak.StackNotLeak;

public class Main {
	public static void main(String[] args) {
		StackLeak stackLeak = new StackLeak();
		for(int i=0; i<100; i++){
			stackLeak.push(i);
		}
		stackLeak.printStack(100);

		System.out.println("----- pop 연산 수행 -----");
		for(int i=0; i<50; i++){
			stackLeak.pop();
		}
		stackLeak.printStack(100);


		StackNotLeak stackNotLeak = new StackNotLeak();
		for(int i=0; i<100; i++){
			stackNotLeak.push(i);
		}
		stackNotLeak.printStack(100);

		System.out.println("----- pop 연산 수행 -----");
		for(int i=0; i<50; i++){
			stackNotLeak.pop();
		}
		stackNotLeak.printStack(100);

	}
}

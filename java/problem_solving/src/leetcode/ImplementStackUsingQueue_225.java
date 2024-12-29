package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// #225 - Implement Stack Using Queue

public class ImplementStackUsingQueue_225 {
	static class MyStack {
		Queue<Integer> elements;

		public MyStack() {
			elements = new LinkedList<>();
		}

		public void push(int x) {
			elements.add(x);
			for(int i=1;i<elements.size();i++){
				elements.add(elements.remove());
			}
		}

		public int pop() {
			return elements.remove();
		}

		public int top() {
			return elements.peek();
		}

		public boolean empty() {
			return elements.size() == 0;
		}
	}
	public static void main(String[] args) {
		MyStack obj = new MyStack();
		obj.push(1);
		obj.push(2);
		obj.push(3);
		int param_2 = obj.pop();  // 2
		System.out.println("param_2 = " + param_2);
		int param_3 = obj.top(); // 2
		System.out.println("param_3 = " + param_3);
		boolean param_4 = obj.empty();
		System.out.println("param_4 = " + param_4);
	}


}

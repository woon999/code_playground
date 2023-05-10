package kakao.intern2021;


// #2 kakao2021intern 표 편집 - 양방향 연결리스트, stack 
import java.util.Stack;

public class ChartEdit2 {

	static Stack<Node> dStack;
	public static void main(String[] args) {
//		int n = 8;
//		int k = 2;
//		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		
		int n = 8;
		int k = 2;
		String[] cmd = { "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		System.out.println(solution(n,k,cmd));
	}
	
	// 양방향 연결리스트 
	static class Node{
		int data;
		Node prev, next;
		
		public Node() {
			/* no-op */
		}
		
		public Node(int data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
		
		public Node remove() {
			prev.next = next;
			next.prev = prev;
			if(this.next.data == -1) {
				return this.prev;
			}else {
				 return this.next;
			}
		}
		public void restore() {
			prev.next = this;
			next.prev = this;
		}
	}

	public static String solution(int n, int k, String[] cmd) {
		Node cursor = init(n);
		dStack = new Stack<>();
		for(int i=0; i<k; i++) {
			cursor = cursor.next;
		}
		
		for(int i=0; i<cmd.length; i++) {
			System.out.println("#" + cursor.data);
			String[] command_line = cmd[i].split(" ");
			char op = command_line[0].charAt(0);
			System.out.print(op);
			if(op=='D') {
				int opNum = Integer.parseInt(command_line[1]);
				System.out.println(" "+opNum);
				cursor = down(cursor, opNum);
			}else if(op=='U') {
				int opNum = Integer.parseInt(command_line[1]);
				System.out.println(" "+opNum);
				cursor = up(cursor, opNum);
			}else if(op=='C') {
				cursor = delete(cursor);
			}else {
				restore();
			}
			System.out.println();
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i=0; i<n; i++) {
			answer.append("O");
		}
		
		while(!dStack.isEmpty()) {
			answer.setCharAt(dStack.pop().data, 'X');
		}
		return answer.toString();
	}
	static Node down(Node cur, int num) {
		for(int i=0; i<num; i++) {
			cur = cur.next;
		}
		return cur;
	}
	static Node up(Node cur, int num) {
		for(int i=0; i<num; i++) {
			cur = cur.prev;
		}
		return cur;
	}
	static Node delete(Node cur) {
		dStack.push(cur);
		cur = cur.remove();
		return cur;
	}
	static void restore() {
		Node bNode = dStack.pop();
		bNode.restore();
	}

	public static Node init(int size) {
		Node initNode = new Node(-1);
		Node prevNode = initNode;
		Node curNode = null;
		
		for(int i=0; i<size; i++) {
			curNode = new Node(i);
			prevNode.next = curNode;
			curNode.prev = prevNode;
			
			prevNode = curNode;
		}
		
		// endNode (idx =size인 노드) 
		Node endNode = new Node(-1);
		curNode.next = endNode; 
		return initNode.next; // 초기값 0번노드 
	}
}

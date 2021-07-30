package baekjoon.ttone.dataStructure;

// #1406 dataStructrue 에디터 - 연결리스트 직접 구현  
import java.io.*;
import java.util.Stack;

public class Editor2 {
	
	static class Node{
		char data;
		Node prev, next;
		
		public Node() {
			/* no-op */
		}
		
		public Node(char data) {
			this.data = data;
			this.prev = prev;
			this.next = next;	
		}
		
		public Node delete() {
			if(this.data == '0') {
				return this;
			}
			else {
				prev.next = next;
				next.prev = prev;
				return this.prev;	
			}
		}
		
		public Node insert(char data) {
			Node addNode = new Node(data);
			if(this.data=='0') { // 커서 위치 맨 처음
				addNode.prev = this;
				addNode.next = this.next;
				next.prev= addNode;
				this.next = addNode;
				return this.next;
			}
			else {
				addNode.next = this.next;
				addNode.prev = this;
				next.prev = addNode;
				this.next = addNode;
				return this.next;
			}
		}
	}
	static Node init(String initLine) {
		Node initNode = new Node('0');
		Node prevNode = initNode;
		Node curNode = null;
		
		int size = initLine.length();
		for(int i=0; i<size; i++) {
			char a = initLine.charAt(i);
			curNode = new Node(a);
			prevNode.next = curNode;
			curNode.prev = prevNode;
			
			prevNode = curNode;
		}
		
		Node endNode = new Node('1');
		curNode.next = endNode;
		return initNode.next; 
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = br.readLine();
		
		Node cursor = init(line);
		for(int i=0; i<line.length()-1; i++) {
			cursor = cursor.next;
		}
		int m = Integer.parseInt(br.readLine());
		int len= line.length();
		for(int i=0; i<m; i++) {
			String op = br.readLine();
			char o = op.charAt(0);
			
			if(o == 'L') {
				if(cursor.data!='0') {
					cursor = cursor.prev;
				}
			}else if(o == 'D') {
				if(cursor.next.data=='1') continue;
				cursor = cursor.next;
			}else if(o == 'B') {
				if(len>0) {
					len--;
					cursor = cursor.delete();
				}
			}else {
				char add = op.charAt(2);
				len++;
				cursor = cursor.insert(add);
			}
		}
		
		while(true) {
			if(cursor.data=='0') break;
			cursor = cursor.prev;
			
		}
		
		while(true) {
			bw.write(cursor.next.data);
			cursor = cursor.next;
			if(cursor.next.data =='1') break;
		}
		
		bw.flush();
		bw.close();
	}
}

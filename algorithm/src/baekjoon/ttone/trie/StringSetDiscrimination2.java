package baekjoon.ttone.trie;


import java.io.*;
import java.util.*;

public class StringSetDiscrimination2 {

	static class TrieNode{
		boolean output;
		char ch = '-'; // 디버깅용 변수 
		Map<Character, TrieNode> child = new HashMap<>();
		TrieNode fail;
		public TrieNode() {}
		
		public void insert(String word) {
			TrieNode curNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				curNode.child.putIfAbsent(c, new TrieNode());
				curNode = curNode.child.get(c);
				curNode.ch = c;
				System.out.print(c +" - ");
				if(i== word.length()-1) {
					System.out.println(" -- is terminal node");
					curNode.output = true;
				}
			}
		}
		
		public void computeFailFunc() {
			Queue<TrieNode> q = new LinkedList<>();
			this.fail = this;
			q.add(this);
			
			while(!q.isEmpty()) {
				TrieNode curNode = q.poll();
				for(int i=0; i<26; i++) {
					char c = (char)(i+97);
					TrieNode childNode = curNode.child.get(c);
					
					if(childNode ==null) continue;
					System.out.println("---");
					System.out.println("cur("+curNode.ch+") -> nxt("+childNode.ch+")");
					
					// 1레벨 노드의 실패 연결은 항상 루트 
					if(curNode == this) { 
						System.out.print(c +" - ");
						childNode.fail = this;
						System.out.println("1 레벨 노드 fail -> root");
					}else { //아닌 경우 부모의 실패 연결을 따라가면서 실패 연결을 찾는다.
						System.out.print(c +" - "); 
						TrieNode failLinkNode = curNode.fail; // t:  부모 실패노드
						System.out.println(failLinkNode.ch);
						
						// 부모 실패노드의 자식노드가 c가 아닌 경우 실패노드 거슬러 탐색하기 
						while(failLinkNode!=this && failLinkNode.child.get(c) == null) {
							System.out.print("부모노드의 실패링크 ("+failLinkNode.ch + ")-> ");
							failLinkNode = failLinkNode.fail;
							System.out.println("("+failLinkNode.ch+")");
						}
						// 부모 실패노드의 자식노드가 c인 경우 실패링크 연결 
						if(failLinkNode.child.get(c) != null) {
							System.out.print("부모노드("+failLinkNode.ch + ")의 실패노드 childNode("+c+") ->");
							failLinkNode = failLinkNode.child.get(c);
						}
						System.out.println("find 노드.fail -> " + failLinkNode.ch);
						childNode.fail = failLinkNode;
					}
					
					// 이 위치에서 끝나는 바늘 문자열이 있으면 추가한다. (출력 링크) 
					if(childNode.fail.output) {
						System.out.println("?");
						childNode.output =true;
					}
					q.add(childNode);
				}
			}
		}
		public boolean ahoCorasick(String word) {
			TrieNode curNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				// 해당 노드가 루트가 아니고 해당 트리에는 더이상 일치하는 문자(c)가 없을 때 실패링크로 이동
				while(curNode != this && curNode.child.get(c) ==null) {
					curNode = curNode.fail;
				}
				
				// 해당되는 문자(c)가 트라이에 있다면 해당 노드로 이동
				if(curNode.child.get(c)!=null) {
					curNode = curNode.child.get(c);
				}
				
				if(curNode.output) {
					return true; // 문자열 집합 중 해당되는 바늘 문자열이 있다면 true 반환 
				}
			}
			return false;  // 해당되는 바늘 문자열이 없다면 false 반환 
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		TrieNode trieSet = new TrieNode();
		System.out.println("# trieNode init");
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			trieSet.insert(str);
		}
		System.out.println("\n# compute FailFunction");
		trieSet.computeFailFunc();
		
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			String qStr = br.readLine();
			if(trieSet.ahoCorasick(qStr)) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
}





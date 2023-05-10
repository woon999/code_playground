package baekjoon.ttone.trie;

// #10256 (다중 문자열 검색) 돌연변이 - 아호코라식(Aho-corasick) 
import java.io.*;
import java.util.*;

public class Mutation {
	static Map<Character, Integer> data = new HashMap<>();
	static {
		data.put('A', 0);
		data.put('G', 1);
		data.put('T', 2);
		data.put('C', 3);
	}
	
	static class TrieNode{
		Map<Integer, TrieNode> child = new HashMap<>();
		boolean output;
		TrieNode fail;
		
		public TrieNode() {}
		
		public void insert(String word) {
			TrieNode curNode = this;
			for(int i=0; i<word.length(); i++) {
				int key = data.get(word.charAt(i));
				curNode = curNode.child
						.computeIfAbsent(key, c -> new TrieNode());
			}
			curNode.output = true;
		}
		
		public void computeFailFunc() {
			Queue<TrieNode> q = new LinkedList<>();
			this.fail = this;
			q.add(this);
			
			while(!q.isEmpty()) {
				TrieNode curNode = q.poll();
				
				for(char c : data.keySet()) {
					int key = data.get(c);
					TrieNode childNode = curNode.child.get(key);
					
					if(childNode == null ) continue;
					
					if(curNode == this) { // level 1 
						childNode.fail = this;
					}else {
						TrieNode failNode = curNode.fail; // 부모의 실패노드 
						
						// 부모 실패노드의 자식노드가 key가 아닌 경우 실패노드 거슬러 탐색하기
						while(failNode != this && failNode.child.get(key) == null) {
							failNode = failNode.fail;
						}
						
						// 부모 실패노드의 자식노드가 key인 경우 실패링크 연결 
						if(failNode.child.get(key) != null) {
							failNode = failNode.child.get(key);
						}
					
						childNode.fail = failNode;
					}
					
					// 실패링크 엔드노드가 output == true 이면, 해당 노드도 output
					if(childNode.fail.output) {
						childNode.output= true;
					}
					q.add(childNode);
				}
			}
		}
		
		public int ahoCorasick(String word) {
			TrieNode curNode = this;
			int cnt =0;
			for(int i=0; i<word.length(); i++) {
				int key = data.get(word.charAt(i));
				while(curNode != this && curNode.child.get(key) == null) {
					curNode = curNode.fail;
				}
				
				if(curNode.child.get(key) != null) {
					curNode = curNode.child.get(key);
				}
				
				if(curNode.output) {
					cnt++;
				}
			}
			return cnt;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			String parent = br.readLine();
			String pattern = br.readLine();

			TrieNode trie = new TrieNode();
			trie.insert(pattern);
			for (int i = 0; i < m; i++) {
				for (int j = i + 1; j <m; j++) {
					trie.insert(reverseString(pattern,i,j+1));
				}
			}
			
			trie.computeFailFunc();
			sb.append(trie.ahoCorasick(parent)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static String reverseString(String tmp, int s, int e) {
		StringBuilder sb = new StringBuilder();
		sb.append(tmp.substring(0, s));
		sb.append(new StringBuilder(tmp.substring(s, e)).reverse());
		sb.append(tmp.substring(e, tmp.length()));
		return sb.toString();
	  }
}



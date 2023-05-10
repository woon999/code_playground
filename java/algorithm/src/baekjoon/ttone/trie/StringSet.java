package baekjoon.ttone.trie;

// #14425 trie 문자열 집합 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StringSet {

	static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;
		
		TrieNode(){
			/* no-op */ 
		}
		
		public void insert(String word) {
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				// tmp childNode에 c없으면 추가 
				trieNode.childNode.putIfAbsent(c, new TrieNode());
				trieNode = trieNode.childNode.get(c);
				
				// 마지막 문자 terminal 
				if(i== word.length()-1) {
					trieNode.terminal = true;
					return;
				}
			}
		}
		
		public boolean contains(String word) { 
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c= word.charAt(i);
				TrieNode node = trieNode.childNode.get(c);
				
				if(node == null) {
					return false; // 다음 문자가 없으면 false 
				}
				trieNode = node;
			}
			
			// 해당 단어로 종료하는 문자가 있는 경우 false
			return trieNode.terminal; 
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		TrieNode tNode = new TrieNode();
		for(int i=0; i<n; i++) {
			String in = br.readLine();
			tNode.insert(in);
		}
		
		int cnt=0;
		for(int i=0; i<m; i++) {
			String str = br.readLine();
			if(tNode.contains(str)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}

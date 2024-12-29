package baekjoon.ttone.trie;

// #5670 트라이(Trie) 휴대폰 자판 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MobilePhoneKeyboard {
	static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;
		
		public TrieNode(){
			/* no-op */
		}
		
		public void insert(String word) {
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				trieNode.childNode.putIfAbsent(c, new TrieNode());
				trieNode = trieNode.childNode.get(c);
				
				if(i==word.length()-1) {
					trieNode.terminal = true;
				}
			}
		}
		
		public int autoModule(String word) { 
			TrieNode trieNode = this;
			int cnt=0;
			for(int i=0; i<word.length(); i++) {
				char c= word.charAt(i);
				TrieNode node = trieNode.childNode.get(c);
				// 문자 시작 키보드 입력 
				if(i==0) {
					cnt++;
				}
				// 중간 terminal 노드 || 경우의 수가 2가지 이상이면 키보드 입력 
				else if(trieNode.terminal || trieNode.childNode.size()>1) {
					cnt++;
				}
				trieNode = node;
			}
			return cnt;
		}
		
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		String line;
		while((line = br.readLine())!=null) {
			try {
				int n = Integer.parseInt(line);
				List<String> inputData = new ArrayList<>();
				TrieNode trie = new TrieNode();
				for(int i=0; i<n; i++) {
					String str = br.readLine();
					inputData.add(str);
					trie.insert(str);
				}
				double res=0;
				for(String str : inputData) {
					res+= trie.autoModule(str);	
				}
				System.out.println(String.format("%.2f",res/inputData.size()));
			}catch(NumberFormatException e) {
				return;
			}
		}
		
		// [g]oodbye  : 1
		// [h]e [a]ven   : 1 2
		//      [l]l     : 1 2
		//          [0]  : 1 2  3  > 8
		
		// [h]       :1
		//     [i]   :2
		//     [e]   :2 > 5/3
		
		// [s] [t] r [u]c       : 3
		// [s] [t] r [u]c[s]  : 4
		// [s] [t] r [e]ss   : 3
		// [s] [o] l ...       : 2
		// [r] id [e]        : 2
		// [r] id [e] [r] s  : 3
		// [r] id [i] c --   : 2  > 19  		
	}
}

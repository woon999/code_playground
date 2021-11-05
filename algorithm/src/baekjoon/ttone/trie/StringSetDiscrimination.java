package baekjoon.ttone.trie;


// TODO : Aho-Corasick 알고리즘 공부하기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StringSetDiscrimination {

	static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;
		
		public TrieNode() {
			/* no-op */
		}
		
		public void insert(String word) {
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				trieNode.childNode.putIfAbsent(c, new TrieNode());
				trieNode = trieNode.childNode.get(c);
				
				if(i== word.length()-1) {
//					System.out.println("insert : " +  word);
					trieNode.terminal = true;
				}
			}
		}
		
		public boolean isStringSet(String word) {
			for(int q=0; q<word.length()-1; q++) {
				TrieNode trieNode = this;
				for(int i=q; i<word.length(); i++) {
					char c = word.charAt(i);
//					System.out.println(c);
					TrieNode node = trieNode.childNode.get(c);
					
					if(node == null) continue;
//					System.out.println(node.childNode.keySet()+"," +node.terminal);
					if(node.terminal) return true;
					trieNode = node;
				}
			}
			
			return false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		TrieNode trieSet = new TrieNode();
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			trieSet.insert(str);
		}
		
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			String qStr = br.readLine();
			if(trieSet.isStringSet(qStr)) {
				sb.append("YES\n");	
			}else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
}

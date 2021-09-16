package baekjoon.ttone.trie;

// #9202 트라이(Trie) Boggle 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Boggle {

	static TrieNode trie;
	static char[][] map;
	static boolean[][] check;
	static List<String> resultList;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {1, 1 ,0 ,-1 ,-1 ,-1, 0, 1};
	static class TrieNode{
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;
		
		public TrieNode() {
			/* no-op */
		}
		
		public void insert(String word) {
			TrieNode curNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				curNode.childNode.putIfAbsent(c, new TrieNode());
				curNode = curNode.childNode.get(c);
				if(i==word.length()-1) {
					curNode.terminal=true;
				}
			}
		}
		
		public boolean isInitWord(char init) {
			TrieNode trieNode = this;
			for(char c : trieNode.childNode.keySet()) {
				if(init == c) return true;
			}
			return false;
		}
	
		public boolean contains(String word, boolean flag) { 
			if(word.length() >8) return false; // 8자 이상 false 
			
			TrieNode trieNode = this;
			for(int i=0; i<word.length(); i++) {
				char c= word.charAt(i);
				TrieNode node = trieNode.childNode.get(c);
				if(node == null) {
					return false;
				}
				trieNode = node;
			}
			
			if(flag) { // 전체 문자열인지 확인 
				return trieNode.terminal; 
			}else return true; // 포함된 문자열인지만 확인 
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int w = Integer.parseInt(br.readLine());
		trie = new TrieNode();
		for(int i=0; i<w; i++) {
			String in = br.readLine();
			trie.insert(in);
		}
		
		br.readLine();
		int b = Integer.parseInt(br.readLine());
		for(int t=0; t<b; t++) {
			
			map = new char[4][4]; 
			for(int i=0; i<4; i++) {
				String line = br.readLine();
				for(int j=0; j<4; j++) {
					char c = line.charAt(j);
					map[i][j] = c;
				}
			}
			
			// map 탐색
			resultList = new ArrayList<>();
			check = new boolean[4][4];
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(trie.isInitWord(map[i][j])) {
						check[i][j] = true;
						search(j,i, ""+ map[i][j]);
						check[i][j] = false;
					}
				}
			}
			
			Collections.sort(resultList);
			int cnt = 0;
			int maxLen= 0;
			int idx=0;
			for(int i=0; i<resultList.size(); i++) {
				int len = resultList.get(i).length();
				if(maxLen < len) {
					idx = i;
					maxLen = len;
				}
				cnt += getScore(resultList.get(i));
			}
			
			sb.append(cnt+" " + resultList.get(idx)+" "+ resultList.size()+"\n");
			if(t != b-1) br.readLine();
		}
		
		System.out.println(sb.toString());	
	}
	
	static void search(int x, int y, String str) {
		if(trie.contains(str, true)) {
			if(!resultList.contains(str)) {
				resultList.add(str);
			}
		}
		
		for(int d=0; d<8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx <0 || ny <0 || nx >3 || ny >3) continue;
			if(check[ny][nx]) continue;
			
			String nxt = str + map[ny][nx];
			if(trie.contains(nxt, false)) {
				check[ny][nx] = true;
				search(nx, ny, nxt);
				check[ny][nx] = false;
			}
		}
	}
	
	static int getScore(String word) {
		if(word.length()<=2) return 0;
		else if(word.length()<=4) return 1;
		else if(word.length()==5) return 2;
		else if(word.length()==6) return 3;
		else if(word.length()==7) return 5;
		else if(word.length()==8) return 11;
		
		return 0;
	}

	
	
}



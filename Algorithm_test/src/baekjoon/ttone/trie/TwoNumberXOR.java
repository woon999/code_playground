package baekjoon.ttone.trie;

// #13505 트라이(Trie) 두 수 XOR 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TwoNumberXOR {

	static int maxHeight = 30;
	static class TrieNode{
		TrieNode[] childNode = new TrieNode[2];
		
		public TrieNode(){
			/* no-op */
		}
		
		public void insert(int num){
			TrieNode cur = this;
			for(int i=maxHeight; i>=0; i--) {
				int status = num & (1<<i);
				
				int nxt = status ==0? 0 : 1;
				System.out.print(nxt);
				if(cur.childNode[nxt] == null) {
					cur.childNode[nxt] = new TrieNode();
				}
				cur = cur.childNode[nxt];
			}
			System.out.println();
		}
		
		public int queryXOR(int num) {
			TrieNode cur = this;
			int res =0;
			for(int i=maxHeight; i>=0; i--) {
				int status = num & (1<<i);
				
				int nxt = status ==0? 1 : 0; // xor이므로 반대로
				if(cur.childNode[nxt] == null) {
					nxt = nxt ==0 ? 1: 0; // 비교되는 값 없으므로 원상태로 복구
				}else {
					res += 1 <<i; 
				}
				cur = cur.childNode[nxt];
			}
			
			return res;
		}
		
		
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TrieNode trie = new TrieNode();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<Integer> inputData = new ArrayList<>();
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			trie.insert(num);
			inputData.add(num);
		}
		System.out.println(Integer.toBinaryString(1_000_000_000));
		int max= -1;
		for(int input : inputData) {
//			System.out.println("in : " +input);
			int res = trie.queryXOR(input);
			max = Math.max(max, res);
		}
		
		System.out.println(max);
	}
	
}
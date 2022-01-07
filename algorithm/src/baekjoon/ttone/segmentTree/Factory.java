package baekjoon.ttone.segmentTree;

// #7578 segmentTree 공장 
import java.io.*;
import java.util.*;

public class Factory {
	
	static int n;
	static long[] tree;
	static long sum(int idx) {
		long res = 0;
		while(idx > 0) {
			res += tree[idx];
			idx -= (idx & -idx);
		}
		return res;
	}
	
	static void update(int idx) {
		while(idx <= n) {
			tree[idx] += 1;
			idx += (idx & -idx);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> first = new HashMap<>();
		Map<Integer, Integer> second = new TreeMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			first.put(Integer.parseInt(st.nextToken()), i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			second.put(first.get(Integer.parseInt(st.nextToken())), i);
		}
		
		tree = new long[n+1];
		long answer = 0;
		for(int key: second.keySet()) {
			int num = second.get(key);
			update(num);
			answer += (key-sum(num));
		}
		
		System.out.println(answer);
		
	}
}

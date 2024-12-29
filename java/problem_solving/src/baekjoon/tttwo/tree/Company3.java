package baekjoon.tttwo.tree;

// #14287 tree 회사 문화 3 - 펜윅 트리 
import java.io.*;
import java.util.*;

public class Company3 {

	static int cnt = 0;
	static int n;
	static int[] in, out;
	static int[] tree;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
			int num = Integer.parseInt(st.nextToken());
			if(num != -1) list[num].add(i);
		}
		
		in = new int[n+1];
		out = new int[n+1];
	
		dfs(1);
		for(int i=1; i<=n; i++) {
			System.out.println(i +" : " +in[i] +" - " + out[i]);
		}
		
		tree = new int[n+1];
		while(m-->0){
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op==1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				update(in[a], b);
				for(int i=1; i<=n; i++) {
					System.out.print(tree[i] +" ");
				}
				System.out.println();
			}else {
				int a = Integer.parseInt(st.nextToken());
				System.out.println(sum(out[a]) - sum(in[a]-1)); 
				
			}
		}
	}
	
	static void update(int idx, int val) {
		while(idx<=n) {
			tree[idx] += val;
			idx += idx&-idx;
		}
	}
	
	static int sum(int idx) {
		int res = 0;
		while(idx>0) {
			res += tree[idx];
			idx -= idx&-idx;
		}
		return res;
	}
	
	static void dfs(int here) {
		in[here] = ++cnt;
		for(int nxt : list[here]) {
			dfs(nxt);
		}
		out[here] = cnt;
	}
}




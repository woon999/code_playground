package baekjoon.ttone.tree;

// #14570 tree 나무 위의 구슬 
import java.io.*;
import java.util.*;

public class TreeOnTheBiz {
	
	static List<int[]>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[i].add(new int[]{a,b});
		}
		
		long k = Long.parseLong(br.readLine());
		dfs(1, k);
	}
	
	static void dfs(int cur, long k) {
		for(int[] nxt : list[cur]) {
			int left = nxt[0];
			int right = nxt[1];
			
			if(left == -1 && right == -1) {
				System.out.println(cur);
				return;
			} else if(left == -1) { // 오른쪽만 있을 때 
				dfs(right, k);
			} else if(right == -1) { // 왼쪽만 있을 때 
				dfs(left, k);
			} else { // 둘다 있을 때 
				if(k%2==1) {
					dfs(left, k/2+1);
				}else {
					dfs(right, k/2);	
				}
			}
		}
	}
}

package baekjoon.ttone.dp;

// #1949 dp 우수마을 (dfs, tree)
import java.io.*;
import java.util.*;

public class BestTown2 {

	static int[] town;
	static List<Integer>[] list;
	static boolean[] check;
	static int[][] memo;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		town = new int[n+1];
		memo = new int[n+1][2];
		list = new ArrayList[n+1];
		check = new boolean[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
//		for(int i=1; i<n+1; i++) {
//			Arrays.fill(memo[i],-1);
//		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			town[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[b].add(a);
			list[a].add(b);
		}

		traversal(1);
		for(int i=1; i<n+1; i++) {
		for(int j=0; j<2; j++) {
			System.out.print(memo[i][j]+" ");
		}
		System.out.println();
	}
		
		System.out.println(Math.max(memo[1][0], memo[1][1]));
	}
	
	static void traversal(int pos) {
		int child_num = list[pos].size();
		
		memo[pos][0] = 0; // 참석 x 
		memo[pos][1] = town[pos]; // 참석 o
		
		if(child_num ==0) return;
		
		check[pos] =true;
		for(int child : list[pos]) {
			if(!check[child]) {
				traversal(child);
			
				// 자식 x  > 자식 o
				if(memo[child][0] > memo[child][1]) {
					memo[pos][0] += memo[child][0]; // 부모 x 자식 x 
					
				}else { //  자식 x  < 자식 o
					memo[pos][0] += memo[child][1]; // 부모 x 자식 o
				}
				
				memo[pos][1] += memo[child][0]; // (공통) 부모 o 자식 x
			}
		}
		check[pos] = false;
	
		
	}

	
}



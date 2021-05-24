package baekjoon.ttone.dp;

// #2213 dp,traversal 트리의 독립집합 
import java.io.*;
import java.util.*;

public class TreeIndependentSet {

	static List<Integer>[] node_list;
	static int[] weight;
	static int[][] memo;
	static List<Integer> res;	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		weight = new int[n+1];
		node_list = new ArrayList[n+1];
		memo = new int[n+1][2];
		res = new ArrayList<>();
		for(int i=0; i<n+1; i++) {
			node_list[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<n+1; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			if(a>b) {
				node_list[b].add(a);
			}else {
				node_list[a].add(b);
			}
		}
//		for(int i=1; i<n+1; i++) {
//        	if(node_list[i].size() >0) {
//        		System.out.println(i+" : " +node_list[i]);
//        	}
//        }
		
		
		traversal(1);
		
//		for(int i=1; i<n+1; i++) {
//			System.out.println(memo[i][0] +" " + memo[i][1]);
//		}
		
		if(memo[1][1]> memo[1][0] ) {
			System.out.println(memo[1][1]);
			trace(1,1);
		}else {
			System.out.println(memo[1][0]);
			trace(1,0);
		}
		
		Collections.sort(res);
		for(int num : res ) {
			System.out.print(num+" ");
		}
		
		
	}
	
	static void traversal(int pos) {

		int child_num = node_list[pos].size();
		
		memo[pos][0] = 0; // 참석 x 
		memo[pos][1] = weight[pos]; // 참석 o
		
		if(child_num ==0) return;
		
		for(int child : node_list[pos]) {
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
	
	static void trace(int pos, int attend) {
		if(attend==1) {
			res.add(pos);
			for(int i=0; i<node_list[pos].size(); i++) {
				int next = node_list[pos].get(i);
				
				trace(next, 0);
			}
		}
		else {
			for(int i=0; i<node_list[pos].size(); i++) {
				int next = node_list[pos].get(i);
				
				if(memo[next][1] > memo[next][0]) {
					trace(next, 1);
				}else {
					trace(next, 0);
				}
			}
		}
		
	}
}

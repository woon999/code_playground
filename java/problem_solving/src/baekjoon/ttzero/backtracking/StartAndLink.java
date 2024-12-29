package baekjoon.ttzero.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink {

	static int n;
	static int[][] team;
	static boolean[] check;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		team = new int[n][n];
		check = new boolean[n];
		
		
		StringTokenizer st;
		for(int i =0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(-1,0);
		System.out.println(min);
	
	}
	
	static void dfs(int start, int count) {
		if(count == n/2) {
			calc();
			return;
		}
		
		
		for(int i=start+1; i<n;i++ ) {
			   if(!check[i]) {
	            	check[i] = true;
	                dfs(i,count+1);
	                check[i] = false;
	            }
		}
	}
	
	static void calc() {
		int start =0, link=0;
		int s[] = new int[n/2];
		int l[] = new int[n/2];
		int s_idx = 0, l_idx = 0;
		
		for(int i =0; i<n; i++) {
			if(check[i]) {
				s[s_idx++] = i;
			}
			else {
				l[l_idx++] = i;
			}
		}
		
		start = power(s);
		link = power(l);
		
		int gap = Math.abs(start-link);
		
		min = Math.min(min, gap);
	}
	
	static int power(int[] arr) {
		int result = 0;
		
		for(int i=0; i< arr.length;i++) {
			for(int j =i+1;j<arr.length;j++) {
				result += team[arr[i]][arr[j]] + team[arr[j]][arr[i]];			
				}
		}
		return result;
	}
}

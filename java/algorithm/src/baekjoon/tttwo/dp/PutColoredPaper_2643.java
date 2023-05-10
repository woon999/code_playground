package baekjoon.tttwo.dp;

// #2643 dp 색종이 올려 놓기 

import java.io.*;
import java.util.*;

public class PutColoredPaper_2643 {
	
	static class Paper implements Comparable<Paper>{
		int x;
		int y;
		
		public Paper(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Paper o) {
			if(o.x == this.x) {
				return this.y-o.y;
			}
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Paper> list = new ArrayList<>(); 
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a < b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			list.add(new Paper(a,b));
		}
		
		Collections.sort(list);
		
		int[] dp = new int[n];
		for(int i=0; i<n; i++) {
			Paper cur = list.get(i);
			dp[i]= 1;
			for(int j=0; j<i; j++) {
				Paper tmp = list.get(j);
				if(cur.y >= tmp.y) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(dp[n-1]);
	}
}

package baekjoon.ttzero.dynamicplanning1;

// #1463
import java.io.*;
import java.util.Arrays;

public class MakeOne {

	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
	
		min = Integer.MAX_VALUE;
		dfs(n,0);
		
		System.out.println(min);

	}

	static void dfs(int n, int cnt) {

		if(n ==1) {
			min = min > cnt ? cnt : min;
			return;
		}
		
		if(cnt >= min) return;
		
		if(n%2==0) {
			dfs(n/2, cnt+1);
		}
		if(n%3==0) {
			dfs(n/3, cnt+1);
		}
		
		dfs(n-1, cnt+1);
		
		
	}
}

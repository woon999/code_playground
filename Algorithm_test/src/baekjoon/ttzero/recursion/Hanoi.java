package baekjoon.ttzero.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hanoi {

	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		func(n,1,2,3);
		System.out.println((int)Math.pow(2,n)-1);
		System.out.println(sb);
	}
	
	public static void func(int n, int from, int by, int to) {
		if(n==1) {
			sb.append(from+" "+ to + "\n");
			return;
		}
		
		func(n-1,from,to,by);
		sb.append(from+" "+to+"\n");
		func(n-1,by,from,to);
		
	}
}

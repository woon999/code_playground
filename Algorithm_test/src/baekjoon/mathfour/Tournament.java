package baekjoon.mathfour;

// #1057

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tournament {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int res =0;
		
		while( a != b) {
			a = (a+1)/2;
			b = (b+1)/2;
			++res;
		}
		
		System.out.println(res);
	}
}

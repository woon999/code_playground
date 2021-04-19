package baekjoon.ttzero.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MovieDirector {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		System.out.println(count(n));
	}
	
	public static int count(int n) {
		
		int cnt = 0;
		int num =0;
		while(n > cnt) {
			if(String.valueOf(++num).contains("666"))
				cnt++;
		}
		
		return num;
	}
}

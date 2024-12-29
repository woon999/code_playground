package baekjoon.ttzero.whilemoon;

import java.io.*;

public class PlusCycle {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int a = Integer.parseInt(br.readLine());
		int temp = a;
		int count = 0;
		while(true) {
			a = ((a%10) *10) + (((a/10)+(a%10))%10);
			count++;
			if(temp == a) break;
		}
		
		System.out.println(count);
	}
}

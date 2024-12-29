package baekjoon.ttzero.dynamicplanning1;

// #1904

import java.io.*;


// 1 2 3 5
// 3 001 100 2 111  3
public class Tile01 {

	static int[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		memo = new int[n+1];
		System.out.println(count(n));
	
	}

	static int count(int n) {
		if (n == 1)
			return 1;
		else if (n == 2)
			return 2;

		else {
			if (memo[n] != 0)
				return memo[n];
		}
		
		memo[n] = (count(n-2)+count(n-1))%15746;
		return memo[n];

	}
}

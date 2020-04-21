package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GetPrimeNumber {

	public static boolean[] primeNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		primeNum = new boolean[n+1];
		getPrimeNum();
		
		StringBuilder sb = new StringBuilder();
		for(;m<=n;m++) {
			if(!primeNum[m]) {
				sb.append(m+"\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	public static void getPrimeNum() {
		primeNum[1] = true;

		for (int i = 2; i < primeNum.length; i++) {
			for (int j = 2; i * j < primeNum.length; j++) {
				primeNum[i * j] = true;
			}
		}
	}
}

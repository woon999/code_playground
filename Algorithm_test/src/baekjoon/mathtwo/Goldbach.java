package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Goldbach {

	public static void main(String[] args) throws Exception {
		boolean[] primeNum = new boolean[10001];
		Arrays.fill(primeNum, true);
		primeNum[1] = false;
		
//		v1
		for (int i = 2; i < primeNum.length; i++) {
			if (primeNum[i]) {
				for (int j = 2; i * j < primeNum.length; j++) {
					primeNum[i * j] = false;
				}
			}
		}
		
//		v2
//		for (int i = 2; i <= 100; ++i) { 
//			if (primeNum[i]) {
//				for (int j = i + i; j <= 10000; j += i)
//					primeNum[j] = false;
//			}
//		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());

			for (int j = 0; j < t; j++) {
				int a = t / 2 - j;
				int b = t / 2 + j;
				if (primeNum[a] && primeNum[b]) {
					System.out.println(a + " " + b);
					break;
				}
			}

		}
	}

}

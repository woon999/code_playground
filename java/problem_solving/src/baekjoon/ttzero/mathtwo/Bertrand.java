package baekjoon.ttzero.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bertrand {

	public static boolean[] primeNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = 0;

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			if(n ==0) break;
			
			primeNum = new boolean[2 * n + 1];
			System.out.println(getPrimeNum(n));

		}
	}

	public static int getPrimeNum(int n) {
		primeNum[1] = true;
		int count = 0;

		for (int i = 2; i < primeNum.length; i++) {
			for (int j = 2; i * j < primeNum.length; j++) {
				primeNum[i * j] = true;
			}
		}

		for (int i = n + 1; i < 2 * n + 1; i++) {
			if (!primeNum[i]) {
				count++;
			}
		}

		return count;
	}
}

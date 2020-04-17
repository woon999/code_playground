package baekjoon.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//1 2 6 7 15 ...
//3 5 8 14 ...
//4 9 13 ...
//10 12  ..
//11 ...
//	a(1) = 1
//	a(2) = 2
//	a(3) = 4
//	a(4) = 7
//	a(n) = a(n-1) + n 

public class FindFraction {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int a = 1;
		int k = 1;
		while (n >= a) {
			a += k;
			k++;
		}

		if ((k - 1) % 2 != 0) {
			System.out.println((a - n) + "/" + (n - (a - k)));
		} else {
			System.out.println((n - (a - k)) + "/" + (a - n));
		}

	}

}

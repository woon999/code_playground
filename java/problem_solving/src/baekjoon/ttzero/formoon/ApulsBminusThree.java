package baekjoon.ttzero.formoon;

import java.util.Scanner;

public class ApulsBminusThree {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int t = s.nextInt();
		int[][] arr = new int[t][2];

		for (int i = 0; i < t; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = s.nextInt();
			}
			System.out.println(arr[i][0]+ arr[i][1]);
		}
		
//		for(int k = 0; k< t ; k++) {
//			System.out.println(arr[k][0]+ arr[k][1]);
//		}
	}
}

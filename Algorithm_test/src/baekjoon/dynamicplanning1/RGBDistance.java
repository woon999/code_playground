package baekjoon.dynamicplanning1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistance {

	static int n;
	static int[][] house;
	static int[][] check;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		house = new int[n][3];
		check = new int[n][3];
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		price(n);
		
		System.out.println(result);
		
	}

	static void price(int count) {
		check[0][0] = house[0][0];
		check[0][1] = house[0][1];
		check[0][2] = house[0][2];

		for (int i = 1; i < count; i++) {
		
			check[i][0] = Math.min(check[i-1][1], check[i-1][2]) + house[i][0];
			check[i][1] = Math.min(check[i-1][0], check[i-1][2]) + house[i][1];
			check[i][2] = Math.min(check[i-1][0], check[i-1][1]) + house[i][2];
			
		}
		
		result = Math.min(check[count-1][0], Math.min(check[count-1][1], check[count-1][2]));

	}
}

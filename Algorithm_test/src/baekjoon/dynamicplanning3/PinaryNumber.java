package baekjoon.dynamicplanning3;

// #2193
import java.io.*;

public class PinaryNumber {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		long[] arr = new long[n];

		if (n == 1) {
			arr[0] = 1;
		}
		else if(n ==2) {
			arr[1] = 1;
		}
		else {
			arr[0] = 1;
			arr[1] = 1;
			
			for(int i=2; i<n; i++) {
				arr[i] = arr[i-1] + arr[i-2];
			}
		}
		
		System.out.println(arr[n-1]);
	}
}

package baekjoon.ttzero.maththree;

// #1037
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Divisor {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int result =1;
		
		result *= arr[0]*arr[n-1];
		
		System.out.println(result);
		
	}
}

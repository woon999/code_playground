package baekjoon.ttzero.maththree;

// #3036
import java.io.*;
import java.util.*;

public class Ring {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		
		for(int i =0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i =1; i<n; i++) {
			System.out.println(arr[0]/gcd(arr[0],arr[i])+"/"+arr[i]/gcd(arr[0],arr[i]));
		}
	}
	static int gcd(int n, int m) {

		if (n % m == 0) {
			return m;
		}
			return gcd(m, n % m);
		
	}
}

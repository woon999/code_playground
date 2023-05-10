package baekjoon.ttone.binarySearch;

// #1365 binarySerach 꼬인 전깃줄 - LIS 
import java.io.*;
import java.util.*;

public class CrossLine {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1];
		int len = 0;
		int idx = 0;
		for(int i=0; i<n; i++) {
//			Arrays.stream(dp).forEach(s -> System.out.print(s +" "));
//			System.out.println();
			if(arr[i] > dp[len]) {
				len += 1;
				dp[len] = arr[i];
				continue;
			} 
			
			idx = binarySearch(0, len, arr[i]);
//			System.out.println("# " + idx + " - " + i);
			dp[idx] = arr[i];
		}
		
		System.out.println(n-len);
				
				
	}
	static int binarySearch(int left, int right, int key) {
		while(left<right) {
			int mid = (left+right)/2;
			if(dp[mid] > key) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return right;
	}
}

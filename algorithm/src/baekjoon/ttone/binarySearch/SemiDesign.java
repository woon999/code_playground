package baekjoon.ttone.binarySearch;

// #2352 binarySerach 반도체 설계 - dp 
import java.io.*;
import java.util.StringTokenizer;

public class SemiDesign {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// solveDP(n,arr);
		
		int[] dp = new int[n+1];
		int len =0;
		int idx =0;
		for(int i=0; i<n; i++) {
			if(arr[i]>dp[len]) {
				dp[++len] = arr[i];
			}else {
				idx = binarySearch(dp, 0, len, arr[i]);
//				System.out.println(arr[i]+ "#" +idx);
				dp[idx] = arr[i];
//				for (idx = 1; idx <= len; idx++) {
//					if (arr[i] <= dp[idx]) break;
//				}
//				dp[idx] = arr[i];
			}
		}
//		for(int num : dp) {
//			System.out.print(num+" ");
//		}
//		System.out.println();
		System.out.println(len);
	}
	
	static int binarySearch(int[] dp, int left, int right, int key) {
		int mid =0;
		while(left<right) {
			mid = (left+right)/2;
			if(dp[mid] < key) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		return right;
	}
	static void solveDP(int n, int[] arr){
		int[] dp = new int[n];
		dp[0] =1;
		for(int i=1; i<n; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[i]>arr[j]) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
		}
		
		int max =-1;
		for(int num : dp) {
			max = Math.max(num, max);
		}
		System.out.println(max);
	}
}




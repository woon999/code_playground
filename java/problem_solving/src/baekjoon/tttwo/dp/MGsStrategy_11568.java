package baekjoon.tttwo.dp;

// #11568 dp 민균이의 계락 - LIS(dp + 이진탐색) 
import java.io.*;
import java.util.StringTokenizer;

public class MGsStrategy_11568 {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1];
		int len = 0;
		int idx = 0;
		for(int i=0; i<n; i++) {
			if(arr[i] > dp[len]) {
				len ++;
				dp[len] = arr[i];
			} else {
				idx = findIdx(0, len, arr[i]);
				dp[idx] = arr[i];
			}
			
		}

		System.out.println(len);
	}
	
	static int findIdx(int l, int r, int key) {
		int mid = 0;
		while(l<r) {
			mid = (l+r)/2;
			if(dp[mid] < key) {
				l = mid+1;
			}else {
				r = mid;
			}
		}
		return r;
	}
}

package baekjoon.ttone.binarySearch;

// #14003 binarySearch 가장 긴 증가하는 부분 수열 5 - LIS
//memo배열 구성은 LIS와 무관함
// 반례 
//4
//10 20 30 5
// LIS : 10 20 30
// memo : 5 20 30 
// 다른 반례 
//6
//1 5 6 2 3 4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LIS5 {
	static int[] lis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
	
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis = new int[n+1];  // 0~i의 arr의 원소배열 중 LIS의 길이가 len인 부분수열들의 마지막 값 중 최솟값
		lis[0] = -1_000_000_001;
		int[] dp = new int[n]; // 증가 부분 수열 크기 저장   
		int len =0;
		int idx =0;
		for(int i=0; i<n; i++) {
			if(arr[i] > lis[len]) {
				dp[i] = ++len;
				lis[len] =arr[i];
				
			}else {
				idx = binarySearch(0, len, arr[i]);
				lis[idx] = arr[i];
				dp[i] = idx;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(len+"\n");
		
		Stack<Integer> s = new Stack<>();
		for(int i=n-1; i>=0; i--) {
			if(dp[i] == len) {
				s.push(arr[i]);
				len--;
			}
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop()+" ");
		}
		System.out.println(sb.toString());
	}
	static int binarySearch(int left, int right, int key) {
		int mid =0;
		while(left < right) {
			mid = (left+right)/2;
			
			if(lis[mid] < key) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		return right;
	}
}

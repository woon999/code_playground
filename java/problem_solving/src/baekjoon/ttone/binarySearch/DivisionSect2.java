package baekjoon.ttone.binarySearch;

// #13397 binarySearch 구간 나누기2 - 파라메트릭 서치 
import java.io.*;
import java.util.StringTokenizer;

public class DivisionSect2 {

	static int n, INF = 987654321;
	static int[] arr;
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int left = 0;
		int right = -1;
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		
		while(left < right) {
			int mid = (left + right)/2;
			if(solve(mid)<=m) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		System.out.println(right);
	}

	static int solve(int mid) {
		int cnt = 1;
		int min = INF;
		int max = -INF;
		for(int i=0; i<n; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			if(max - min > mid) {
				cnt++;
				max = -INF; min = INF;
				i--;
			}
		}
		return cnt;
	}
}



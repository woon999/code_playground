package baekjoon.ttone.binarySearch;

// #2512 parametric serach 예산  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Budget {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int left=0, right=-1;
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		
		int m = Integer.parseInt(br.readLine());
		while(left<=right) {
			int mid = (left+right)/2;
			long budget =0;
			for(int i=0; i<n; i++) {
				if(arr[i]>mid) budget += mid;
				else budget+= arr[i];
			}
			
			if(budget<=m) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		System.out.println(right);
	}
	
}

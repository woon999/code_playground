package baekjoon.ttone.binarySearch;

// #2805 parametric serach 나무 자르기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutTree {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		int left = 0;
		int right = -1;
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		
		while(left<=right) {
			int mid = (left+right)/2;
			long tree=0;
			for(int i=0; i<n; i++) {
				if(arr[i]>mid) tree+= arr[i]-mid;
			}
			if(tree>=m) {
				left = mid+1;
			}else if(tree<m) { 
				right = mid-1;
			}
		}
		
		System.out.println(right);
	}
	
	static long cutting(int n, int mid, int[] arr) {
		long tree=0;
		for(int i=0; i<n; i++) {
			if(arr[i]>mid) tree+= arr[i]-mid;
		}
		return tree;
	}
}

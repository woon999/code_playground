package baekjoon.ttzero.binarysearch;

// #1920
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindNumber {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		int m = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			System.out.println(binary(A,Integer.parseInt(st.nextToken())));
		}
		
		
	}
	
	static int binary(int[] arr, int n) {
		int high = arr.length-1, low =0, mid=0;
		
		while(low <= high) {
			mid = (high + low)/2;
			
			if(arr[mid] == n) {
				return 1;
			}else if(arr[mid]>n) {
				high = mid -1;
			}else {
				low = mid+1;
			}
			
		}
		return 0;
	}
	
}

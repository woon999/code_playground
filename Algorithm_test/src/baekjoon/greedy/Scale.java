package baekjoon.greedy;

// #2437
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scale {

	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr= new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		Arrays.sort(arr);
		
		System.out.println(solve());
		
		
	}
	
	static int solve() {
		if(arr[0] != 1 ) return 1;
		
		int sum = arr[0];
		for(int i=1; i<n; i++) {
			if(arr[i] > sum+1) return sum+1;
			sum += arr[i];
			
		}
		return sum+1;
	}
}

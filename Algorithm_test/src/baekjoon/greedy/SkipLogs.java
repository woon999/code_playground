package baekjoon.greedy;


// #11497
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SkipLogs {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test = Integer.parseInt(br.readLine());
		
		for(int t=0; t<test; t++) {
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr= new int[n];
 			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
 			
 			
 			Arrays.sort(arr);
 			
 			int result = -1;
 			
 			for(int i=0; i<n-2; i++) {
 				result = Math.max(result, Math.abs(arr[i]-arr[i+2]));
 			}
 			
 			
 			System.out.println(result);
		}
	}
}

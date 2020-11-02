package baekjoon.greedy;

// #14659
import java.io.*;
import java.util.StringTokenizer;

public class Hanzo {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int res=0;
		for(int i=0; i<n; i++) {
			int idx = arr[i];
			int cnt =0;
			for(int j = i+1; j<n; j++) {
				if(arr[j] > idx) break;
				else cnt++;
				
				res = Math.max(res, cnt);
				
			}
		}
		
		System.out.println(res);
		
	}
}

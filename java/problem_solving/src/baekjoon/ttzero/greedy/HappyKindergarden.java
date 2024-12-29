package baekjoon.ttzero.greedy;

// #13164
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HappyKindergarden {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new  StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		int[] sub = new int[n-1];
		
		for(int i=1; i<n; i++) {
			sub[i-1] = arr[i] -arr[i-1];
		}
		Arrays.sort(sub);
		
		
		int res =0;
		for(int i=0; i<n-k; i++) {
			res+= sub[i];
		}
		
		System.out.println(res);
		
		
	}
}

package baekjoon.ttone.greedy;

// #2437 그리디 저울 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scale {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum=0;
		for(int i=0; i<n; i++) {
			
			if(arr[i] > sum+1) break;
			sum+= arr[i];
		}
		
		System.out.println(sum+1);
	}
}

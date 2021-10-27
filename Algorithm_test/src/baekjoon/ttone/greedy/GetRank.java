package baekjoon.ttone.greedy;

// #2012 greedy 등수 매기기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GetRank {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		int[] ranks = new int[n+1];
		for(int i=1; i<n+1; i++) {
			ranks[i] = arr[i-1];
		}
		
		long total=0;
		for(int i=1; i<n+1; i++) {
			total += Math.abs(i-ranks[i]);
		}
		System.out.println(total);
	}
}

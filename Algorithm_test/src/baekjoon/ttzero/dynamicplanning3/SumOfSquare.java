package baekjoon.ttzero.dynamicplanning3;

// #1699
import java.io.*;

public class SumOfSquare {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[100001];
		
		for(int i=1; i<100001; i++) {
			for(int j=1; j*j <= i; j++) {
				if(arr[i - j*j]+1 < arr[i] || arr[i] ==0) {
					arr[i] = arr[i -j*j]+1;
				}
			}
		}
		
		System.out.println(arr[n]);
	}
}

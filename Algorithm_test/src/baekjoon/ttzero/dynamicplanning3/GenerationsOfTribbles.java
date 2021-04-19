package baekjoon.ttzero.dynamicplanning3;

// #9507
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GenerationsOfTribbles {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		long[] arr = new long[69];
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		
		for(int i=4; i<68; i++) {
			arr[i] = arr[i-1] + arr[i-2] + arr[i-3] + arr[i-4];
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(arr[n] +"\n");
		}
		
		
		System.out.println(sb.toString());
	}
}

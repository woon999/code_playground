package baekjoon.ttzero.greedy;

// #11501
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stock {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
	
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			int max=0;
			long result=0;
			
			int[] arr = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=n-1; j>=0; j--) {
				if(max < arr[j]) {
					max = arr[j];
				}else {
					result += max-arr[j];
				}	
			}
			
			System.out.println(result);
			
		}
	}
	
}

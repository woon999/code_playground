package baekjoon.ttzero.greedy;

// #2872
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Library {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
		}
		
		int start = 1;
		int sum = 0;
		if(n <= 1) {
			System.out.println(0);
		}
		else {
			if(arr[0] > 1) {
				sum += arr[0] - 1;
				start = arr[0];
			}
			
			for(int i = 1; i < n; i++) {
				if(start + 1 < arr[i]) {
					sum += arr[i] - start;
					start = arr[i];
				}
				
				if(start + 1 == arr[i]) {
					start = arr[i];
				}
			}
			
			System.out.println(sum);
		}
	}
}

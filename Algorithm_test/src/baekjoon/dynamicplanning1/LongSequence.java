package baekjoon.dynamicplanning1;

// #11053
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongSequence {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] rating = new int[n];		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		rating[0] =1;
		
		for(int i =1; i<n; i++) {
			rating[i] =1;
			for(int j=0; j<i; j++) {
				if(arr[j]<arr[i] && rating[i] <= rating[j]) {
					rating[i] = rating[j] + 1;
				}
			}
		}
		
		int result = 0;
		for(int i : rating) {
			result = Math.max(result, i);
		}
		
		System.out.println(result);
		
		
	}
}

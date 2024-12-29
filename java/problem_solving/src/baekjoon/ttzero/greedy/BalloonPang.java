package baekjoon.ttzero.greedy;

// #11509
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BalloonPang {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		
		
		int[] arr = new int[1000001];
		int cnt =0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int m = Integer.parseInt(st.nextToken());
			
			if(arr[m]==0) {
				cnt++;
				arr[m-1]++;
			}
			else if(arr[m] > 0) {
				arr[m]--;
				arr[m-1]++;
			}
		}
		System.out.println(cnt);
		
		
		
		
		
		
	}
}

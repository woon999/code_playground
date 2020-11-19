package baekjoon.dynamicplanning3;

// #11048
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Moving {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n+1][m+1];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<m+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i=1 ;i<n+1; i++) {
			for(int j=1 ;j<m+1; j++) {
				arr[i][j] += Math.max(arr[i][j-1], Math.max(arr[i-1][j-1], arr[i-1][j]));
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println(arr[n][m]);
		
	}
}

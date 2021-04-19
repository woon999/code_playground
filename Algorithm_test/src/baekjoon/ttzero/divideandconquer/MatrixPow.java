package baekjoon.ttzero.divideandconquer;

// #10830
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MatrixPow {

	static int div =1000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		int[][] arr = new int[n][n];
		int[][][] result = new int[10000][n][n];
	
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				if(i==j) {
				arr[i][j] = 1;
				}
				result[0][i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int idx=1;
		while(Math.pow(2, idx) <=b) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						result[idx][i][j] += (result[idx-1][i][k] *result[idx-1][k][j])%div;
					}
				}
			}
			idx++;
		}
		
		while(b>0) {
			idx =0;
			while(Math.pow(2, idx+1)<=b) {
				idx++;
			}
			System.out.println(idx);
			b-=Math.pow(2, idx);
			int next[][] = new int[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						next[i][j] = (next[i][j]+ arr[i][k] *result[idx][k][j])%div;
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				System.arraycopy(next[i], 0, arr[i], 0, n);
			}
			
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

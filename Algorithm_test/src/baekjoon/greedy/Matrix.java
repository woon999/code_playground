package baekjoon.greedy;

// #1080
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Matrix {

	static int n,m;
	static int[][] a;
	static int[][] b;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception{
	
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		a = getMatrix();
		b = getMatrix();
		
		solve();
		
		
	}
	
	static void solve() {
		int cnt =0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(a[i][j]!= b[i][j]) {
					if(flip(i,j)) {
						cnt++;
					}else {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(cnt);
		
	}
	static boolean flip(int sa, int sb) {
		if(sa+3>n || sb+3>m) return false;
		
		for(int i=sa; i<sa+3; i++) {
			for(int j=sb; j<sb+3; j++) {
				a[i][j] = 1 - (a[i][j]);
			}
		}
		
		return true;
	}
	
	static int[][] getMatrix() throws IOException {
		int[][] matrix = new int[n][m];
		for(int i=0; i<n; i++) {
			String row = br.readLine();
			for(int j=0; j<m; j++) {
				matrix[i][j] = row.charAt(j)-'0';
			}
		}
		return matrix;
	}
	
	
}

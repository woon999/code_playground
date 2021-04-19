package baekjoon.ttzero.mathfour;

// #11444
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci6 {
	
	static int div = 1_000_000_007;
	static long[][] mat1 = { {1,1} , {1,0} };
	static long[][] mat2 = { {1,0} , {0,1} };

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Long.parseLong(br.readLine());
		
		
		while(n > 0) {
			if(n%2 == 1) {
				solve(mat2, mat1);
			}
			
			n /= 2;
			solve(mat1, mat1);
		}
		
		System.out.println(mat2[1][0]);
		
	}
	
	
	static void solve(long m1[][], long m2[][]) {
	
		long[][] tmp = new long[2][2];
		long num =0;
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				num =0;
				for(int k=0; k<2; k++){
					num += (m1[i][k] * m2[k][j]);
				}
				tmp[i][j] = num % div;
			}
		}
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				m1[i][j] = tmp[i][j];
			}
		}
	}
}

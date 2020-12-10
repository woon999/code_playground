package baekjoon.dynamicplanning3;

// #9625
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BABBA {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int A = 0, B = 1, sum =0;
		
		for(int i=1; i<n; i++) {
			sum = A+B;
			A =B;
			B= sum;
		}
		
		System.out.println(A+" "+B);
	}
}

package baekjoon.ttzero.mathfour;

// #2576
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Odd {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int res=0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<7; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(n%2 !=0 ) {
				res += n;
				min = (min < n) ? min : n;
			}
			
			
			
			
		}
		
		if(res ==0) {
			System.out.println(-1);
		}else {
		System.out.println(res);
		System.out.println(min);
		}
	}
}


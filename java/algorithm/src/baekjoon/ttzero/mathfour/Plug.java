package baekjoon.ttzero.mathfour;

// #2010
import java.io.*;

public class Plug {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n==1) {
			System.out.println(br.readLine());
			
			return;
		}
		
		int sum = 0;
		for(int i=0; i<n-1; i++) {
			int hole = Integer.parseInt(br.readLine());
			sum += hole-1;
			
		}
		sum += Integer.parseInt(br.readLine());
		System.out.println(sum);
	}
}

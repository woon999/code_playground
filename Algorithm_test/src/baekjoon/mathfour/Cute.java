package baekjoon.mathfour;

// #10886
import java.io.*;

public class Cute {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int one_count = 0;
		int zero_count =0;
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num ==0) {
				zero_count++;
			}else {
				one_count++;
			}
		}
		
		System.out.println((zero_count > one_count ? "Junhee is not cute!" : "Junhee is cute!"));
		
		
	}
	
}

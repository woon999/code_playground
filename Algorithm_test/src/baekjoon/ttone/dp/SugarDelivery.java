package baekjoon.ttone.dp;

// #2839 dp 설탕배달 
import java.io.*;

public class SugarDelivery {
	
	static int count =0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		solve(n);
		
	}
	
	static void solve(int n) {
		
		while(true) {
			if(n%5 ==0) {
				count += n/5;
				System.out.println(count);
				return;
			}else {
				n -= 3;
				count++;
			}
			
			if(n<0) {
				System.out.println(-1);
				return;
			}
	
		}
		
	}
}

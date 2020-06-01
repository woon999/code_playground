package baekjoon.maththree;

// #5086
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MultiplesAndFactors {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if(n==0&&m==0) break;
			
			if(m%n ==0) {
				System.out.println("factor");
			}
			else if(n%m ==0) {
				System.out.println("multiple");
			}else {
				System.out.println("neither");
			}
			
		}
		
	}
}

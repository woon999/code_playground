package baekjoon.ttzero.mathfour;

// #1476
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WeatherCalculator {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int e = 1, s = 1, m = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int year =1;
		while(true) {
			
			if(e == E && s==S && m==M) {
				System.out.println(year);
				break;
			}
			
			e+=1;
			s+=1;
			m+=1;
			
			if(e==16) {
				e=1;
			}
			if(s==29) {
				s=1;
			}
			if(m==20) {
				m=1;
			}
			
			year++;
		}
	}
}

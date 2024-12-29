package baekjoon.ttzero.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FourthDot {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int x[] = new int[1001];
		int y[] = new int[1001];
		for(int i =0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[Integer.parseInt(st.nextToken())]++;
			y[Integer.parseInt(st.nextToken())]++;
		}
		
		for(int i =0;i<1001;i++) {
			if(x[i]%2==1) {
				System.out.print(i+" ");
			}
		}
		
		for(int i =0;i<1001;i++) {
			if(y[i]%2==1) {
				System.out.print(i);
			}
		}
	}
}

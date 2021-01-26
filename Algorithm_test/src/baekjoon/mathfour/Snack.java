package baekjoon.mathfour;

// #10156
import java.io.*;
import java.util.StringTokenizer;

public class Snack {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		if(k * n > m) {
			System.out.println(k * n - m);
		}else {
			System.out.println("0");
		}
		
		
	}
}

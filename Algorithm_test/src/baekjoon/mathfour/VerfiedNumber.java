package baekjoon.mathfour;

// #2475
import java.io.*;
import java.util.StringTokenizer;

public class VerfiedNumber {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num =0;
		for(int i=0; i<5; i++) {
			num += Math.pow(Integer.parseInt(st.nextToken()),2);
		}
		
		System.out.println(num%10);
	}
}

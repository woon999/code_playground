package baekjoon.mathfour;


// #2420
import java.io.*;
import java.util.StringTokenizer;

public class Safari {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		
		System.out.println(Math.abs(a-b));
	}
}

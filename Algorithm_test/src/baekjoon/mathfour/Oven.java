package baekjoon.mathfour;


// #2525

import java.io.*;
import java.util.StringTokenizer;

public class Oven {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(br.readLine());
		
		int sum = b+c;
		
		a += sum/60;
		b += c - (60 * (sum/60));
		
		if(a >= 24) {
			a -= 24;
		}
		
		System.out.println(a + " " + b);
	}
}


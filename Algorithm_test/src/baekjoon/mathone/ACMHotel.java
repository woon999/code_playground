package baekjoon.mathone;

import java.io.*;
import java.util.StringTokenizer;

public class ACMHotel {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i =0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int h = Integer.parseInt(st.nextToken()); //È£ÅÚÀÇ Ãþ ¼ö
			int w = Integer.parseInt(st.nextToken()); //°¢ÃþÀÇ ¹æ ¼ö 
			int n = Integer.parseInt(st.nextToken()); //¸î¹øÂ° ¼Õ´Ô

			hotel(h,w,n);
			
		}
		
	}
	
	public static void hotel(int h, int w, int n) {
		
		int y = n%h;
		int x = n/h +1;
		if(n%h==0) {

			x = n/h;
			y= h;
		}
		
		System.out.println(y*100+x);
		
	}
}

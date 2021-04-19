package baekjoon.ttzero.mathfour;

// #2845

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AfterParty {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int l = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<5; i++) {
			int news = Integer.parseInt(st.nextToken());
			sb.append(news-l*p + " ");	
		}
		
		
		System.out.println(sb.toString());
		
	}
}

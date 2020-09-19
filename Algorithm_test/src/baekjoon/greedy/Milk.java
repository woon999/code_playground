package baekjoon.greedy;

// #14720
import java.io.*;
import java.util.StringTokenizer;

public class Milk {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int cnt =0;
		int[] rule = {0,1,2};
		int rIdx=0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			
			if(Integer.parseInt(st.nextToken()) == rule[rIdx%3]) {
				cnt ++;
				rIdx++;
			}
		}
	
		System.out.println(cnt);
	}
}

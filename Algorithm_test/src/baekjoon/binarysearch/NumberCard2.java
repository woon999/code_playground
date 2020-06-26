package baekjoon.binarysearch;

// #10816
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberCard2 {
	
static int standard = 10000000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] card = new int[standard*2+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			card[Integer.parseInt(st.nextToken())+standard]++;
		}
		
		int m = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			System.out.print(card[Integer.parseInt(st.nextToken())+standard]+" ");
		}
		
		
	}
	
	
}

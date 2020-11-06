package baekjoon.greedy;

// #2879
import java.io.*;
import java.util.StringTokenizer;

public class GoodCoding {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] tab = new int[n];
		int[] rTab = new int[n];
		int cnt =0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			tab[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			rTab[i] = Integer.parseInt(st.nextToken()) - tab[i];
		}
		
		int idx = rTab[0];
		for(int i= 1; i<n; idx = rTab[i++]) {
			if(idx * rTab[i] <0) cnt += Math.abs(idx);
			else if(Math.abs(idx) > Math.abs(rTab[i])) {
				cnt += Math.abs(idx - rTab[i]);
			}
		}
		
		System.out.println(cnt+ Math.abs(idx));
		
	}
}

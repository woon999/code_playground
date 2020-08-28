package baekjoon.greedy;

// #1783
import java.io.*;
import java.util.StringTokenizer;


public class SickKnight {

	static int n,m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		System.out.println(solve());
	}
	
	static int solve() {
		
		if(n==1) return 1;
		else if(n==2) {
			return Math.min(4, (m+1)/2);
		}
		if(m<7) {
			return Math.min(4,m);
		}
		return m-2; // 4+m-6
	}
}

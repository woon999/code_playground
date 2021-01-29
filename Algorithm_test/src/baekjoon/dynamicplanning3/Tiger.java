package baekjoon.dynamicplanning3;

// #2502
import java.io.*;
import java.util.StringTokenizer;

public class Tiger {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		if(d==3) {
			System.out.println(1);
			System.out.println(k-1);
			return;
		}
		
		int[] a1 = new int[d+1];
		int[] a2 = new int[d+1];
		
		a1[3] = a2[3] =1;
		a1[4] = 1;
		a2[4] = 2;
		
		for(int i=5; i<d+1; i++) {
			a1[i] = a1[i-1] + a1[i-2];
			a2[i] = a2[i-1] + a2[i-2];
		}
		
		int first = 0;
		int second = 0;
		int idx = 0;
		
		while(true) {
			idx++;
			int res = k - a1[d]*idx;
			
			if(res % a2[d] == 0) {
				first = idx;
				second = res/ a2[d];
				break;
			}
		}
		
		System.out.println(first);
		System.out.println(second);
	}
}

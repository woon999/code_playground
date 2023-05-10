package baekjoon.tttwo.number;

// #2916 number 자와 각도기 
import java.io.*;
import java.util.StringTokenizer;

public class RulerAndProtractor {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		boolean[] degree = new boolean[361];
		st = new StringTokenizer(br.readLine());
		
		while(n-- > 0) {
			int d = Integer.parseInt(st.nextToken());
			degree[d] = true;
			int i = 0;
			while(true) {
				for(int j=0; j<360; j++) {
					if(degree[j]) {
						degree[(i+j)%360] = degree[Math.abs(i-j)] = true;
					}
				}
				i = (i+d)%360;
				if(i == 0) break;
			}
			
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<k; i++) {
			if(!degree[Integer.parseInt(st.nextToken())]) {
				sb.append("NO\n");
			}else {
				sb.append("YES\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}
}

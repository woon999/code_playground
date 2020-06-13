package baekjoon.queuedeque;

import java.io.*;

public class AC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			String[] s = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			String[] x = br.readLine().replace("[", "").replace("]", "").split(",");
			
//			String result = ac(p,n,x);
			
//			System.out.println(result);
		}
	}
}

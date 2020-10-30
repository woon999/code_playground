package baekjoon.mathone;

// #10953

import java.io.*;
import java.util.StringTokenizer;

public class AplusBSix {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] num = st.nextToken().split(",");
			
			System.out.println(Integer.parseInt(num[0])+ Integer.parseInt(num[1]));
			
			
			
		}
	}
}

package baekjoon.ttzero.formoon;

import java.io.*;

public class OutputN {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i<=n; i++) {
			bw.write(i+"\n");
		}
		bw.flush();
	}
	
}

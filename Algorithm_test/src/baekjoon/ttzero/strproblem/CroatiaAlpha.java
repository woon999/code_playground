package baekjoon.ttzero.strproblem;

import java.io.*;

public class CroatiaAlpha {

	private static String[] croatia = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine().trim();
		
		for(int i=0;i<croatia.length;i++) {
			if(s.contains(croatia[i])) {
				s = s.replace(croatia[i], "a");
			}
		}
		
		System.out.println(s.length());
	}
}

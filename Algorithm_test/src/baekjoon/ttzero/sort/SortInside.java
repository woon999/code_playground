package baekjoon.ttzero.sort;

import java.io.*;
import java.util.*;

public class SortInside {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split("");
		
		Arrays.sort(s,Collections.reverseOrder());
		
		for(int i =0; i< s.length; i++) {
			bw.write(s[i]);
		}
		br.close();
		bw.close();
	}
}

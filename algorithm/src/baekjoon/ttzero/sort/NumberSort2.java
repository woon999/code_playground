package baekjoon.ttzero.sort;

import java.io.*;
import java.util.*;

public class NumberSort2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		ArrayList<Integer> num = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			num.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(num);

		for (int i = 0; i < n; i++) {
			bw.write(num.get(i)+"\n");
			
		}
		
		bw.flush();
		bw.close();

	}

}

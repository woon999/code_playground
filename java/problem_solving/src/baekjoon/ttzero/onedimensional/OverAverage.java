package baekjoon.ttzero.onedimensional;

import java.io.*;
import java.util.*;

public class OverAverage {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c = Integer.parseInt(br.readLine());

//		double[] percent = new double[c];
		
		for (int i = 0; i < c; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] student = new int[Integer.parseInt(st.nextToken())];

			double average = 0;
			for (int j = 0; j < student.length; j++) {
				student[j] = Integer.parseInt(st.nextToken());
				average += student[j];
			}
			
			average /= student.length;

			double count = 0;
			for (int k = 0; k < student.length; k++) {
				if (student[k] > average)
					count++;
			}

			double result = (count / student.length) * 100;
			System.out.println(String.format("%.3f", result) + "%");
//			for (int l = 0; l < c; l++) {
//				percent[i] = result;
//			}

		}

//		for (int i = 0; i < c; i++)
//			System.out.println(String.format("%.3f", percent[i]) + "%");

	}
}

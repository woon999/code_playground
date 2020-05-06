package baekjoon.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class AgeSort {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String[][] s = new String[n][2];
		
		for(int i =0; i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s[i][0] = st.nextToken();
			s[i][1] = st.nextToken();
		}
		
		
		Arrays.sort(s, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(Integer.parseInt(o1[0]),Integer.parseInt(o2[0]));
			}
		});
		
		
		for(int i=0;i<n;i++) {
			System.out.println(s[i][0] + " " + s[i][1]);
		}
	}
}

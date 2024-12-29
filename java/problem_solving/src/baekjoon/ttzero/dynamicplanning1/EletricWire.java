package baekjoon.ttzero.dynamicplanning1;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class EletricWire {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] elec = new int[n+1][2];
		int[] memo = new int[n+1];
		
		StringTokenizer st;
		for(int i =1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			elec[i][0] = Integer.parseInt(st.nextToken());
			elec[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(elec, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			} 
		});
		
		int result =0;
		for(int i =1; i<n+1; i++) {
			memo[i] =1;
			for(int j =1; j<i; j++) {
				if(elec[j][1]< elec[i][1]) {
					memo[i] =Math.max(memo[i], memo[j]+1);
				}
 			}
			result = Math.max(result, memo[i]);
		}
		System.out.println(n- result);
	}
}

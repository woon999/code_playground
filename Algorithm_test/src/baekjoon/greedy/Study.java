package baekjoon.greedy;

// #13904

import java.io.*;
import java.util.*;

public class Study {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n =Integer.parseInt(br.readLine());
		
		int[][] task = new int[n][2];
		
		int result =0;
		int idx=0;
		
		for(int i=0; i<n; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			
			task[i][0] = Integer.parseInt(st.nextToken());
			task[i][1] = Integer.parseInt(st.nextToken());
			
		}
		
		
		Arrays.sort(task, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[1], o1[1]);
			}
			
		});
		
		int[] score = new int[1001];
		for(int i=0; i<n; i++) {
			for(int j=task[i][0]; j>0; j--) {
				if(score[j] ==0) {
					score[j] = task[i][1];
					break;
				}
			}
		}
		
		
		for(int i=0; i<task.length; i++) {
			result += score[i];
		}
		
		
		System.out.println(result);
	
	
	}
}

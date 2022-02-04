package baekjoon.tttwo.binarysearch;

// #8983 binarysearch 사냥꾼 
import java.io.*;
import java.util.*;

public class Hunter {

	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		long l = Long.parseLong(st.nextToken());
		
		int[] tower = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] animal = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			animal[i][0] = Integer.parseInt(st.nextToken());
			animal[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tower);
		int cnt =0;
		for(int i=0; i<n; i++) {
			int s =0;
			int e =m;
			while(s<=e) {
				int mid = (s+e)/2;
				long dis = Math.abs(tower[mid]-animal[i][0]) + animal[i][1];
				
				if(dis <= l) {
					cnt++;
					break;
				}
				
				if(animal[i][0] < tower[mid]) {
					e = mid-1;
				}else {
					s = mid+1;
				}
			}
			
		}
		System.out.println(cnt);
	}
}

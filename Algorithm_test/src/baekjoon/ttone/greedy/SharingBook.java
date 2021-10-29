package baekjoon.ttone.greedy;

// #9576 greedy 책 나눠주기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SharingBook {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			boolean[] check = new boolean[n+1];
			List<int[]> list = new ArrayList<>();
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());	
				list.add(new int[] {a,b});
			}
			
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1]-o2[1];
				}
			});
			
			int total=0;
			for(int[] num : list) {
				int start =num[0];
				int end = num[1];
				for(int i=start; i<=end; i++) {
					if(!check[i]) {
						check[i] = true;
						total++;
						break;
					}
				}
				System.out.println(num[0] +" - " + num[1]);
			}
			sb.append(total+"\n");
		}
		System.out.println(sb.toString());
	}
}
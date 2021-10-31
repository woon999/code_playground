package baekjoon.ttone.greedy;

// #13904 greedy 과제 - 높은 점수 순대로 가장 마감일 가깝게 풀이하기 
import java.io.*;
import java.util.*;

public class Study2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		List<int[]> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.add(new int[] {d,w});
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1]-o1[1];
			}
		});
		
		int[] check = new int[1001];
		for(int[] p : list) {
			for(int i=p[0]; i>0; i--) {
				if(check[i]==0) {
					check[i] = p[1];
					break;
				}
			}
		}
		
		int total =0;
		for(int num : check) {
			total += num;
		}
		System.out.println(total);
		
	}
}

package baekjoon.tttwo.graph;

// #14567 graph 선수과목 - 위상정렬 
import java.io.*;
import java.util.*;

public class Prerequisite_14567 {
	
	static List<Integer>[] list;
	static int[] in, result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		in = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			in[b]++; // 진입차수 
		}

		result = new int[n+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			if(in[i] == 0) {
				q.add(i);
			}
			result[i] = 1;
		}
		
		while(!q.isEmpty()) {
			int here = q.poll();
			
			for(int nxt : list[here]) {
				in[nxt]--;
				if(in[nxt] == 0) {
					q.add(nxt);
					result[nxt] = Math.max(result[nxt], result[here] + 1);
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			System.out.print(result[i]+" ");
		}
		System.out.println();
	}
}

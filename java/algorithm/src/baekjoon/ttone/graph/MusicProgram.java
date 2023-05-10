package baekjoon.ttone.graph;

// #2623 grpah 음악프로그램 - 위상정렬 
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MusicProgram {

	static int n;
	static StringBuilder sb = new StringBuilder();
	static List<Integer>[] list;
	static Queue<Integer> q;
	static int[] ranks;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		ranks = new int[n+1];
		for(int i=0; i<m; i++) {
			String[] line = br.readLine().split(" ");
			int size = Integer.parseInt(line[0]);
			for(int j=2; j<size+1; j++) {
				int a = Integer.parseInt(line[j-1]);
				int b = Integer.parseInt(line[j]);
				
				list[a].add(b);
				ranks[b]++;
			}
		}
		
		q = new LinkedList<>();
		for(int i=1; i<n+1; i++) {
			if(ranks[i]==0) {
				q.add(i);
			}
		}
		topologySort();
		
		boolean flag = false;
		for(int i=1; i<n+1; i++) {
			if(ranks[i] !=0) {
				flag=true;
				break;
			}
		}
		if(flag) System.out.println(0);
		else System.out.println(sb.toString());
		
	}
	
	static void topologySort() {
		while(!q.isEmpty()) {
			int pos = q.poll();
			sb.append(pos+"\n");
			for(int nxt : list[pos]) {
				ranks[nxt]--;
				if(ranks[nxt]==0) {
					q.add(nxt);
				}
			}
		}
	}
}

package baekjoon.tttwo.tree;

// #2132 tree 나무 위의 벌레 
import java.io.*;
import java.util.*;

public class BugOfTree {

	static int n;
	static int[] data, vertex;
	static Queue<Integer> q;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		data = new int[n+1];
		vertex = new int[n+1];
		list = new ArrayList[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			vertex[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}

		int[] f =  bfs(1);
		int[] r =  bfs(f[1]);
		System.out.println(r[0] +" " + Math.min(f[1], r[1]));
	}
	
	
	static int[] bfs(int s) {
		int endIdx = 0, maxDis = -1;
		Queue<Integer> q = new LinkedList<>();
		Arrays.fill(data, -1);
		q.add(s);
		data[s] = vertex[s];
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			if(data[pos] > maxDis) {
				endIdx = pos;
				maxDis = data[pos];
			}else if(data[pos] == maxDis) {
				if(endIdx > pos) endIdx = pos;
				maxDis = data[pos];
			}
			
			for(int nxt : list[pos]) {
				if(data[nxt] != -1) continue;
				data[nxt] = data[pos] + vertex[nxt];
				q.add(nxt);
				
			}
		}
		return new int[] {maxDis, endIdx};
	}
	
}


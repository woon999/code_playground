package baekjoon.ttone.tree;

// #2132 tree 나무 위의 벌레 
import java.io.*;
import java.util.*;

public class BugOfTree {

	static int r, start, INF = 987654321;
	static int[] data;
	static Queue<Integer> q;
	static List<int[]> end;
	static List<int[]>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		data = new int[n+1];
		list = new ArrayList[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			data[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(new int[] {b, data[b]});
			list[b].add(new int[] {a, data[a]});
		}

		q = new LinkedList<>();
		dfs(1, 0, data[1]);
		
		r = 0;
		end = new LinkedList<>();
		for(int num : q) {
			dfs2(num, num, data[num], num);
		}
		
		Collections.sort(end, (o1, o2) -> (o1[0] - o2[0]));
		System.out.println(r +" " + end.get(0)[0]);
	}
	
	static void dfs(int idx, int pa, int cost) {
		if(cost > r) {
			r = cost;
			q.clear();
			
			q.add(idx);
		}else if(cost == r) {
			q.add(idx);
		}
		
		for(int[] nxt : list[idx]) {
			if(nxt[0] != pa) {
				dfs(nxt[0], idx, cost+nxt[1]);
			}
		}
	}
	
	static void dfs2(int idx, int pa, int cost, int start) {
		if(cost > r) {
			r = cost;
			end.clear();
			
			if(start < idx) {
				end.add(new int[] {start, idx});
			}else {
				end.add(new int[] {idx, start});
			}
		}else if(cost == r) {
			if(start < idx) {
				end.add(new int[] {start, idx});
			}else {
				end.add(new int[] {idx, start});
			}
		}
		
		for(int[] nxt : list[idx]) {
			if(nxt[0] != pa) {
				dfs2(nxt[0], idx, cost+nxt[1], start);
			}
		}
	}
}


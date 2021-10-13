package baekjoon.ttone.graph;

// #17471 graph 게리맨더링 - 구현, 비트마스킹 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Gerrymandering {

	static int n, fullStat, answer=Integer.MAX_VALUE;
	static int[] people;
	static List<Integer>[] list;
	static Set<Integer> set = new HashSet<>(); 
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new ArrayList[n+1];
		people = new int[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int jl = Integer.parseInt(st.nextToken());
			for(int j=0; j<jl; j++) {
				int b = Integer.parseInt(st.nextToken());
				list[i].add(b);
			}
		}
		fullStat = (1<<n+1) -1;
		for(int i=0; i<n-1; i++) {
			int stat = 1<<1;
			dfs(1,people[1], i, stat);
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
 
	static void dfs(int idx, int cost, int r, int stat) {
		if(r == 0) {
			if(set.add(stat)) {
				getDiff(stat);
			}
			return;
		}
		
		for(int i=idx; i<n; i++) {
			stat |= 1<<(i+1);
			dfs(i+1, cost+people[i+1], r-1, stat);
			stat ^= 1<<(i+1);
		}
	}
	
	static void getDiff(int stat) {
		int a = stat;
		int b = fullStat^(a);
		
		int aCost, bCost;
		if((aCost=linking(a)) != -1& (bCost= linking(b)) != -1) {
			answer = Math.min(answer, Math.abs(aCost-bCost));
		}
		
	}
	static int linking(int stat) {
		List<Integer> city = new ArrayList<>();
		for(int i=1; i<n+1; i++) {
			if((stat & (1<<i)) == 1<<i) {
				city.add(i);
			}
		}
		
		check = new boolean[n+1];
		int st = city.get(0);
		check[st] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(st);
		int cost = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cost += people[cur];
			for(int nxt : list[cur]) {
				if(!check[nxt] && city.contains(nxt)) {
					check[nxt] = true;
					q.add(nxt);
				}
			}
		}
		for(int i : city) {
			if(!check[i]) return -1;
		}
		return cost;
	}
}

//10
//1 2 3 4 5 6 7 8 9 10
//2 10 2
//2 1 3
//2 2 4
//2 3 5
//2 4 6
//2 5 7
//2 6 8
//2 7 9
//2 8 10
//2 9 1
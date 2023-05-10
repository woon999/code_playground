package baekjoon.tttwo.segmenttree;

// #2517 segmenttree 달리기 - fenwick 
import java.io.*;
import java.util.*;

public class Run_Fenwick {
	
	static class Runner{
		int idx;
		int skill;
		
		public Runner(int idx, int talent) {
			this.idx = idx;
			this.skill = talent;
		}
	}

	static int n;
	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		List<Runner> runners = new ArrayList<>();
		for(int i=0; i<n; i++) {
			runners.add(new Runner(i, Integer.parseInt(br.readLine())));
		}
		
		Collections.sort(runners, (a,b)-> (a.skill - b.skill));
		for(int i=0; i<runners.size(); i++) {
			Runner runner = runners.get(i);
			runner.skill = i+1;
		}
		Collections.sort(runners, (a,b)-> (a.idx - b.idx));
		
		StringBuilder sb = new StringBuilder();
		tree = new int[n+1];
		for(int i=1; i<=n; i++) {
			int skill = runners.get(i-1).skill;
			sb.append(i - sum(skill-1) +"\n");
			update(skill,1);
			for(int j=1; j<n+1; j++) {
				System.out.print(tree[j]+" ");
			}
			System.out.println();
		}
		System.out.println(sb.toString());
	}
	
	static int sum(int idx) {
		int result = 0;
		while(idx > 0) {
			result += tree[idx];
			idx -= (idx & -idx);
		}
		return result;
	}
	
	static void update(int idx, int val) {
		while(idx <= n) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}
}

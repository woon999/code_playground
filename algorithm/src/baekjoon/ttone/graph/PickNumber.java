package baekjoon.ttone.graph;

// #2668 dfs 숫자고르기 
import java.io.*;
import java.util.*;

public class PickNumber {

	static List<Integer> answer;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		answer = new ArrayList<>();
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
			int num = Integer.parseInt(br.readLine());
			list[i].add(num);
		}
		
		Stack<Integer> s;
		for(int i=1; i<=n; i++) {
			s = new Stack<>();
			dfs(i,s,i);
		}
		
		System.out.println(answer.size());
		Collections.sort(answer);
		for(int num : answer) {
			System.out.println(num);
		}
	}
	
	static void dfs(int idx, Stack<Integer> s, int pa) {
		if(s.size()>0 && idx == pa) {
			if(!answer.contains(pa)) answer.add(pa);
			return;
		}
		for(int nxt : list[idx]) {
			if(!s.contains(nxt)) {
				s.push(nxt);
				dfs(nxt, s, pa);
				s.pop();
			}
		}
	}
}

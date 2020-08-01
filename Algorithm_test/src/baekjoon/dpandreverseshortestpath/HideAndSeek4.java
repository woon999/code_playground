package baekjoon.dpandreverseshortestpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class HideAndSeek4 {
	
	static int n,k;
	static int[] pos;
	static int[] trace;
	static int[] dx = {-1,1,2};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		pos = new int[100001];
		trace = new int[100001];
		bfs();
		sb.append(pos[k]+"\n");
		Stack<Integer> s = new Stack<Integer>();
		s.push(k);
		int i =k;
		while(i != n) {
			s.push(trace[i]);
			i = trace[i];
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop()+" ");
		}
	
		
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		
		int nPos = n;
		while(!q.isEmpty() && nPos != k) {
			nPos = q.poll();
			
			for(int i=0; i<3; i++) {
				int nx;
				if(i==2) {
					nx = nPos *dx[i];
				}else {
					nx = nPos +dx[i];
				}
				
				if(nx >=0 && nx<100001 && pos[nx]==0) {
					q.add(nx);
//					System.out.println("nx : "+ nx+ ", nPos :"+ nPos);
					trace[nx] = nPos;
					pos[nx] = pos[nPos] +1;
					
				}
			}
		}
	}
	
}

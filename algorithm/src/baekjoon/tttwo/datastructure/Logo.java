package baekjoon.tttwo.datastructure;

// bj datastructure (3108. 로고) - union-find
// 좌표*2로 해서 기하학으로도 풀이 가능 

import java.io.*;
import java.util.*;

public class Logo {

	static class Square{
		int x1,y1,x2,y2;

		public Square(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		public boolean isTouched(Square other) {
			// 내부1. other가 this 내부에 속해있는 경우  
			if(this.x1 < other.x1 && this.y1 < other.y1 && other.x2 < this.x2 && other.y2 < this.y2) {
				return false;
			}
			
			// 내부1. this가 other 내부에 속해있는 경우  
			if(other.x1 < this.x1 && other.y1 < this.y1 && this.x2 < other.x2 && this.y2 < other.y2) {
				return false;
			}
			
			// 외부. 서로가 맞닿아 있지 않을 경우 
			if(this.x2 < other.x1 || this.y2 < other.y1  || this.x1 > other.x2 || this.y1 > other.y2) {
				return false;
			}
			
			return true;
		}
	}
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Square[] sq = new Square[n+1];
		parents= new int[n+1];
		for(int i=0; i<=n; i++) {
			parents[i] = i;
		}
		
		StringTokenizer st;
		for(int i=0,x1,y1,x2,y2; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			sq[i] = new Square(x1, y1, x2, y2);
		}
		sq[n] = new Square(0,0,0,0);
		
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				if(i==j) continue;
				if(sq[i].isTouched(sq[j])) {
					union(i, j);
				}
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<=n; i++) {
			set.add(find(parents[i]));
		}
		System.out.println(set.size()-1);
	}
	
	static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parents[y] = x;
		}else {
			parents[x] = y;
		}
	}
		
		
}

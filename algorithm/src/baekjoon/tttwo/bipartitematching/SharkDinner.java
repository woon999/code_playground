package baekjoon.tttwo.bipartitematching;
 
// #1671 bipartitematching 상어의 저녁식사 
import java.io.*;
import java.util.*;

public class SharkDinner {
	static class Shark{
		int size;
		int speed;
		int intelligence;
		
		public Shark(int size, int speed, int intelligence) {
			this.size = size;
			this.speed = speed;
			this.intelligence = intelligence;
		}

	}

	static int n;
	static List<Integer>[] list;
	static int[] eat;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		Shark[] sharks = new Shark[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			sharks[i] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				int cond = compareShark(sharks[i],sharks[j]);
				if(cond == 1 || (cond == 2 && i<j)) list[i].add(j);
			} 
		}
		
		eat = new int[n+1];
		check = new boolean[n+1];
		int cnt = 0;
		
		for(int i=1; i<=n; i++) {
			System.out.print(i +" - ");
			for(int nxt : list[i]) {
				System.out.print(nxt+" ");
			}
			System.out.println();
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(check, false);
				if(dfs(i))cnt++;	
			}
		}
		
		System.out.println(cnt);
		for(int i=1; i<=n; i++) {
			System.out.println(i +" - " + eat[i]);
		}
		System.out.println(n-cnt);
		
	}
	static int compareShark(Shark s1, Shark s2) {
		int cnt = 0;
		if(s1.size < s2.size) return 0;
		if(s1.size == s2.size)  cnt++;
		if(s1.speed < s2.speed) return 0;
		if(s1.speed == s2.speed)  cnt++;
		if(s1.intelligence < s2.intelligence) return 0;
		if(s1.intelligence == s2.intelligence)  cnt++;
		if(cnt == 3) return 2;
		return 1;
	}
	
	static boolean dfs(int here) {
		for(int nxt : list[here]) {
			if(check[nxt]) continue;
			check[nxt] = true;
			
			if(eat[nxt] == 0 || dfs(eat[nxt])) {
				eat[nxt] = here;
				return true;
			}
		}
		return false;
	}
}

package baekjoon.ttone.implementation;

// #1913 implementation 달팽이 
import java.io.*;

public class Snail {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int t = Integer.parseInt(br.readLine());
		solve1(n,t);
//		solve2(n,t);
		
	}
	
	static void solve2(int n, int t){
		int[][] map = new int[n][n];
		int value =1;
		
		int x = n/2, y=n/2;
		
		int limit =1;
		while(true) {
			for(int i=0; i<limit; i++) {
				map[y--][x] = value++;
			}
			if(value-1 == n*n) break;
			for(int i=0; i<limit; i++) {
				map[y][x++] = value++;
			}
			
			limit++;
			for(int i=0; i<limit; i++) {
				map[y++][x] = value++;
			}
			
			for(int i=0; i<limit; i++) {
				map[y][x--] = value++;
			}
			limit++;
		}
		
		StringBuilder sb = new StringBuilder();
		int tx=0, ty=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(t==map[i][j]) {
					ty=i+1;
					tx=j+1;
				}
				sb.append(map[i][j] +" ");
			}
			sb.append("\n");
		}
		sb.append(ty+" "+tx);
		System.out.println(sb.toString());
	}
	
	static void solve1(int n, int t) {
		int[][] map = new int[n][n];
		int value = n*n;
		int x = 0,y = 0;
		int time = 0;
		int limit = n;
		while(value>0) {
			x=time;
			for(int i=y; i<limit; i++) {
				map[i][x] = value--;
			}
			
			y=limit-1;
			for(int i=x+1; i<limit; i++) {
				map[y][i] = value--;
			}
			
			x=limit-1;
			for(int i=y-1; i>=time; i--) {
				map[i][x] = value--;
			}
			
			y=time;
			for(int i=x-1; i>time; i--) {
				map[y][i] = value--;
			}
			time++;
			limit--;
			y=time;
		}
		
		StringBuilder sb = new StringBuilder();
		int tx=0, ty=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(t==map[i][j]) {
					ty=i+1;
					tx=j+1;
				}
				sb.append(map[i][j] +" ");
			}
			sb.append("\n");
		}
		sb.append(ty+" "+tx);
		System.out.println(sb.toString());
	}
	
}

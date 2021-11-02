package baekjoon.ttone.implementation;

// #1913 implementation 달팽이 
import java.io.*;

public class Snail {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int t = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		int value = n*n;
		int tx=0, ty=0;
		int x=0,y=0;
		int time=0;
		int limit =n;
		while(value>0) {
			x=time;
			for(int i=y; i<limit; i++) {
				if(t== value) {
					tx=x+1;
					ty=i+1;
				}
				map[i][x] = value--;
			}
			
			y=limit-1;
			for(int i=x+1; i<limit; i++) {
				if(t== value) {
					tx=i+1;
					ty=y+1;
				}
				map[y][i] = value--;
			}
			
			x=limit-1;
			for(int i=y-1; i>=time; i--) {
				if(t== value) {
					tx=x+1;
					ty=i+1;
				}
				map[i][x] = value--;
			}
			
			y=time;
			for(int i=x-1; i>time; i--) {
				if(t== value) {
					tx=i+1;
					ty=y+1;
				}
				map[y][i] = value--;
			}
			time++;
			limit--;
			y=time;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sb.append(map[i][j] +" ");
			}
			sb.append("\n");
		}
		sb.append(ty+" "+tx);
		System.out.println(sb.toString());
	}
	
}

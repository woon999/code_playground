package baekjoon.ttone.implementation;

// #17143 implementation 낚시왕 
import java.io.*;
import java.util.*;

public class FishingKing {
	static class Shark{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static int r,c;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[r][c];
		
		Map<Integer, Shark> sharkList = new HashMap<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharkList.put(i+1, new Shark(y,x,s,d,z));
			map[y][x] = i+1;
		}
		
//		print(map);
		int result=0;
		int pos =-1;
		for(int test=0; test<c; test++) {
			// 1. fishing
			pos++;
			for(int i=0; i<r; i++) {
				if(map[i][pos] >0) {
					int sharkNumber = map[i][pos];
					System.out.println(sharkNumber +" shark get!");
					result += sharkList.get(sharkNumber).z; // 상어 잡음
					// 상어 제거 
					sharkList.remove(sharkNumber);
					map[i][pos]=0;
					break;
				}
			}
			
			// 2. shark move
			for(int key : sharkList.keySet()){
//				System.out.print(key+" ");
				Shark sh = sharkList.get(key);
				// d 1위 2아래 3오른쪽 4왼쪽
				map[sh.r][sh.c] = 0;
				if(sh.d == 1)  {
					// r--;
					if(moving(sh,-1,sh.d)==1) sh.d = 2;
				}else if(sh.d == 2) { 
					// r++;
					if(moving(sh,1,sh.d)==-1) sh.d = 1;
				}else if(sh.d == 3) {
					// c++;
					if(moving(sh,1,sh.d)==-1) sh.d = 4;
				}else {
					// c--;
					if(moving(sh,-1,sh.d)==1) sh.d = 3;
				}
			}
//			System.out.println();
			
			List<Integer> removeList = new ArrayList<>();
			// 같은 위치 
			for(int key : sharkList.keySet()){
				Shark sh = sharkList.get(key);
//				System.out.println("#" + key);
				if(map[sh.r][sh.c] > 0)	{
					if(sharkList.get(map[sh.r][sh.c]).z > sh.z) {
						removeList.add(key);	
//						System.out.println(key +" 가 "+ map[sh.r][sh.c] +" 에 먹힘 ");
					}else {
						removeList.add(map[sh.r][sh.c]);
//						System.out.println(map[sh.r][sh.c]+" 가 "+ key  +" 에 먹힘 ");
						map[sh.r][sh.c] =key;
					}
				}else {
					map[sh.r][sh.c] =key;
				}
			}
			
			for(int key : removeList) {
				sharkList.remove(key);
			}
//			for(int key : sharkList.keySet()){
//				System.out.print(key +" ");
//			}
//			System.out.println();
//			print(map);
		}
		System.out.println(result);
	}
	static int moving(Shark sh, int d, int type) {
		int move = sh.s;
		while(move>0) {
			if(type==1|| type==2) {
				if(sh.r==0) d = 1;
				else if(sh.r==r-1) d = -1;
				sh.r+= d;
			}else {
				if(sh.c==0) d = 1;
				else if (sh.c==c-1) d = -1;
				sh.c+= d;
			}
			move--;
		}
		return d;
	}
	static void print(int[][] map) {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println();
	}
		
}

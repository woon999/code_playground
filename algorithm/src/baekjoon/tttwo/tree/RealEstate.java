package baekjoon.tttwo.tree;

// #20364 tree 부동산 다툼 
import java.io.*;
import java.util.*;

public class RealEstate {

	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new int[n+1];
		for(int i=0; i<q; i++) {
			int duck = Integer.parseInt(br.readLine());
			int x = up(duck, 0);
			if(x==0) {
				tree[duck]++;	
			}
			System.out.println(x);
		}
	}
	
	static int up(int here, int block) {
		if(here < 2) {
			return block;
		}
		
		if(tree[here] !=0) {
			block = here;
		}
		return up(here>>1, block);
	}
	
}

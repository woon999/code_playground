package baekjoon.tttwo.greedy;

// #1493 greedy	박스 채우기 
import java.io.*;
import java.util.StringTokenizer;

public class FillBox {

	static int[] box, cube;
	static int n;
	static long fullV;
	static boolean f = true;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		box = new int[3];
		fullV = 1;
		for(int i=0; i<3; i++) {
			box[i] = Integer.parseInt(st.nextToken());
			fullV *= box[i];
		}
		n = Integer.parseInt(br.readLine());
		
		cube = new int[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		// dfs 
		System.out.println(solve(n-1, 0, 0));
		
		// divide-conquer
		int res = divide(box[0], box[1], box[2]);
		if(f) {
			System.out.println(res);
		}else {
			System.out.println(-1);
		}
	}
	
	static int divide(int l, int w, int h) {
		if(l == 0 || w == 0 || h == 0 ) return 0;
		int k = Math.min(l, Math.min(w, h));
		
		int t = (int)(Math.log(k)/Math.log(2))+1;
		while(t--> 0) {
			if(n <= t || cube[t] <= 0)continue;
			cube[t]--;
			int size = (int)Math.pow(2, t);
			return divide(l-size, w, h) + divide(size, w-size, h) + divide(size, size, h-size)+1;
		}
		f = false;
		return -1;
	}
	
	static long solve(int blockSize, long fill, long cnt) {
		long totalV = 1;
		for(int i=0; i<3; i++) {
			totalV *= box[i] >> blockSize;
		}
		
		int curCube = cube[blockSize];
		totalV -= fill;
		long fillCube = Math.min(curCube, totalV);
		if(blockSize == 0) {
			if((fill + fillCube) != fullV) {
				return -1;
			}
			return cnt+fillCube;
		}else {
			return solve(blockSize-1, fill+fillCube<<3, cnt+fillCube);	
		}
	}
}




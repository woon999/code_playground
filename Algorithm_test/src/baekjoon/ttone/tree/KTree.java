package baekjoon.ttone.tree;

// #11812 tree K진트리 - LCA 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KTree {

	static long n;
	static int k, h;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Long.parseLong(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			if(k==1) {
				sb.append(Math.abs(x-y)+"\n");
			}else {
				sb.append(LCA(x,y)+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static long LCA(long x, long y) {
		long mv=0;
		// x,y depth
		long xh = getDepth(x);
		long yh = getDepth(y);
		
		if(xh < yh) {
			long tmp = x;
			x = y;
			y = tmp;
			long ttmp = xh;
			xh = yh;
			yh = ttmp;
		}
		
		while(xh != yh) {
			x = getParent(x);
			xh = getDepth(x);
			mv++;
		}
		
		while(x!=y) {
			x = getParent(x);
			y = getParent(y);
			mv+=2;
		}
		return mv;
	}
	
	static long getParent(long idx) {
		return (idx-2)/k+1;
	}
	
	static long getDepth(long idx) {
		if(idx==1) return 0;
		
		long line =1;
		long h =1;
		while(true) {
			line += (long)Math.pow(k,h++);
			if(idx <= line) break;
		}
		
		return h-1;
	}
}
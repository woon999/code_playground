package baekjoon.tttwo.segmenttree;

// #2268 fenwickTree 수들의 합 7 
import java.io.*;
import java.util.StringTokenizer;

public class SumOfNum7 {
	static int[] arr;
	static long[] tree;
	static int n;
	
	static void add(int pos, long val) {
		while(pos <= n) {
			tree[pos] += val;
			pos += (pos&-pos);
		}
	}
	
	static long sum(int pos) {
		long result = 0;
		while(pos > 0) {
			result += tree[pos];
			pos &= (pos-1);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		tree = new long[n+1];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(op == 0) {
				if(b < a) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				sb.append(sum(b)-sum(a-1)+"\n");
			}else {
				long dif = b - arr[a];
				arr[a] = b;
				add(a, dif);
			}
		}
		System.out.println(sb.toString());
	}
}


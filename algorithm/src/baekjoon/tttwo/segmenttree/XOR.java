package baekjoon.tttwo.segmenttree;

// #12844 segmenttree XOR 
import java.io.*;
import java.util.StringTokenizer;

public class XOR {
	static int n;
	static int[] arr, tree, lazy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		tree = new int[4*(n+1)];
		lazy = new int[4*(n+1)];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1,n,1);
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken())+1;
			int b = Integer.parseInt(st.nextToken())+1;
			if(a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			if(op == 1) {
				int v = Integer.parseInt(st.nextToken());
				update(1,n,1,a,b,v);
			} else {
				sb.append(pXOR(1,n,1,a,b)+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static int init(int s, int e, int node) {
        if (s == e) {
            return tree[node] = arr[s];
        }
        int mid = (s+e)/2;
        return tree[node] = init(s, mid, node*2)^init(mid + 1, e, node*2+1);
    }
	
	
	static void update(int s, int e, int node, int l, int r, int dif) {
		propagate(s, e, node);
		if(r < s ||  e < l) return;
		if(l <= s && e <= r) {
			lazy[node] = dif;
			propagate(s, e, node);
			return;
		}
		int mid = (s+e)/2;
        update(s, mid, node*2, l, r, dif);
        update(mid + 1, e, node*2+1, l, r, dif);
        tree[node] = tree[node*2] ^tree[node*2+1];
	}
	
	static int pXOR(int s, int e, int node, int l, int r) {
		propagate(s, e, node);
		if(r < s ||  e < l) return 0;
		if(l <= s && e <= r) {
			return tree[node];
		}
		
		int mid = (s+e)/2;
		return pXOR(s, mid, node*2, l, r)^pXOR(mid+1, e, node*2+1, l, r);
	}
	
	static void propagate(int s, int e, int node) {
		if(lazy[node] != 0) {
			if(s!=e) {
				lazy[node*2] ^= lazy[node];
				lazy[node*2+1] ^= lazy[node];
			}
			if((e-s+1)%2 == 1) { // a^a = 0
				tree[node] ^= lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	static void print() {
		for(int i=1; i<4*n; i++) {
			System.out.print(tree[i] +" ");
		}
		System.out.println();
	}
}

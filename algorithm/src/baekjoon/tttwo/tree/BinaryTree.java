package baekjoon.tttwo.tree;

// #13325 tree 이진트리 
import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

public class BinaryTree {

	static int n, k, res;
	static int[] arr, tree;
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		n = (int)Math.pow(2, k+1)-1;
		arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=2; i<=n; i++) {
			arr[i] =Integer.parseInt(st.nextToken());
		}
		
		dfs(1,0);
		System.out.println(res);
	}
	
	static int dfs(int idx, int h) {
		if(h == k) {
			res += arr[idx];
			return arr[idx];
		}
		
		int left =  dfs(idx*2, h+1);
		int right = dfs(idx*2+1, h+1);
		res += arr[idx]+Math.abs(left -right);
		return arr[idx]+Math.max(left, right);
		
		
	}
}

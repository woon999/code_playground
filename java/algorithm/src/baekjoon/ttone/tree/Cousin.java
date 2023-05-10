package baekjoon.ttone.tree;

// #9489 tree 사촌 
import java.io.*;
import java.util.StringTokenizer;

public class Cousin {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(n==0 && k==0) break;
			int target = 0;
			int[] arr = new int[n+1];
			int[] parent = new int[n+1];
			int idx = -1;
			parent[0] = -1;
			arr[0] = -1;
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] == k) target = i;
				if(arr[i] != arr[i-1]+1) idx++;
				parent[i] = idx;
			}
			
		
			int answer = 0;
			for(int i=1; i<=n; i++) {
				if(parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]]) {
	//				System.out.println(i +" = " +parent[parent[i]] +","+ parent[parent[target]]);
					answer++;
				}
			}
			sb.append(answer+"\n");
			
		}
		System.out.println(sb.toString());
	}
	
}

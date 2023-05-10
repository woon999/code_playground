package baekjoon.tttwo.tree;

// #23844 tree 트리 정리하기 - greedy
/**
 * greedy - 가장 깊은 depth부터 시작해서 childNode가 가장 작은 노드부터 삭제
 * 
 * 그런데 해당 문제는 남은 노드 번호를 구하는 것이 아니라 수를 카운트하는 것이기 때문에 
 * 그냥 모든 레이어를 최대 k개씩 카운트해주기만 해도 됨
 */


import java.io.*;
import java.util.*;

public class TreeCut {

	static int maxLevel = -1;
	static List<Integer>[] list;
	static List<Integer>[] layers;
	static int[] parent; // 부모 노드 
	static int[] childsCnt; // i 노드가 갖고있는 자식 노드 수 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		childsCnt = new int[n+1];
		list = new ArrayList[n+1];
		layers = new ArrayList[n+1];
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
			layers[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(1, 0, 0);
		
		int cnt = 0;
		for(int i=maxLevel; i>=0; i--){
//			System.out.println(layers[i]);
			int size = layers[i].size();
			if(size == 0) break;
			
			if(size > k) {
				Collections.sort(layers[i], (a,b) -> (childsCnt[a] - childsCnt[b]));
				
				// delete node 
				for(int j=0; j<size-k; j++	) {
					int node = layers[i].get(j);
//					System.out.println(node +" - " + childsCnt[node] +", pa :" + parent[node]);
					
					// parent child 감소  
					childsCnt[parent[node]] -= childsCnt[node];
				}
				cnt += k;
				
			}else {
				cnt += size;
			}
		}
		System.out.println(cnt);
	}
	
	
	static int dfs(int here, int pa, int level) {
		int cnt = 0;
		layers[level].add(here);
		parent[here] = pa;
		maxLevel = Math.max(level, maxLevel);
		for(int nxt : list[here]) {
			if(nxt != pa) {
				cnt += dfs(nxt, here, level+1) + 1;
			}
		}
		childsCnt[here] = cnt;
		return cnt;
	}
}

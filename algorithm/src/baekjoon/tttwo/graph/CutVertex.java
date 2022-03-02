package baekjoon.tttwo.graph;

// #11400 graph 단절선 
import java.io.*;
import java.util.*;

public class CutVertex {

	static List<Integer>[] list;
	static List<int[]> cutVtx;
	static int[] discovered;
	static int counter;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		
		cutVtx = new ArrayList<>();
		discovered = new int[v+1];
		list = new ArrayList[v+1];
		for(int i=0; i<=v; i++) {
			list[i] = new ArrayList<>();
			discovered[i] = -1;
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		
		for(int i=1; i<=v; i++) {
			if(discovered[i] == -1) {
				findCutVertex(i, 0);
			}
		}
		
		Collections.sort(cutVtx, (a,b) -> (a[0] == b[0]) ? a[1]-b[1] : a[0]-b[0]);
		StringBuilder sb = new StringBuilder();
		int cnt = cutVtx.size();
		for(int i=0; i<cutVtx.size(); i++) {
			sb.append(cutVtx.get(i)[0]+" "+cutVtx.get(i)[1]);
			sb.append("\n");
		}
		
		System.out.println(cnt);
		if(cnt > 0) {
			System.out.println(sb.toString());	
		}
	}
	
	static int findCutVertex(int cur, int root) {
		discovered[cur] = counter++;
		int ret = discovered[cur];
		for(int i=0; i<list[cur].size(); i++) {
			int nxt = list[cur].get(i);
			if(nxt == root) continue;
			if(discovered[nxt] == -1) {
				// 해당 서브트리에서 갈 수 있는 가장 먼저 발견된 정점 번호
				int subTree = findCutVertex(nxt, cur);
				
				// 그 노드(subTree)가 자기 자신 이하에 있다면 단절선
				if(subTree > discovered[cur]) {
					cutVtx.add(new int[] {Math.min(cur, nxt), Math.max(cur, nxt)});
				}
				ret  = Math.min(ret, subTree); // 가장 먼저 발견된 정점 찾기
			}else {
				ret  = Math.min(ret, discovered[nxt]); // 가장 먼저 발견된 정점 찾기
			}
		}
		
		return ret;
	}
}

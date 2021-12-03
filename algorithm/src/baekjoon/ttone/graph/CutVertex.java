package baekjoon.ttone.graph;

// #11266 graph 단절점 
import java.io.*;
import java.util.*;

public class CutVertex {

	static List<Integer>[] list;
	static boolean[] isCutVertex;
	static int[] discovered;
	static int counter;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		isCutVertex = new boolean[v+1];
		discovered = new int[v+1];
		list = new ArrayList[v+1];
		for(int i=1; i<v+1; i++) {
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
		
		for(int i=1; i<v+1; i++) {
			if(discovered[i] == -1) {
				findCutVertex(i,true);	
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i=1; i<v+1; i++) {
			if(isCutVertex[i]) { 
				cnt++;
				sb.append(i+" ");
			}
			
		}
		
		System.out.println(cnt);
		if(cnt > 0) {
			System.out.println(sb.toString());	
		}
		
	}
	
	static int findCutVertex(int cur, boolean isRoot) {
		discovered[cur] = counter++;
//		System.out.println("+++ " + cur + " : " + discovered[cur]);
		
		int ret = discovered[cur];
		int child =0;
		for(int i=0; i<list[cur].size(); i++) {
			int nxt = list[cur].get(i);
			if(discovered[nxt] == -1) {
				child++;
				
				// 해당 서브트리에서 갈 수 있는 가장 먼저 발견된 정점 번호 
				int subTree = findCutVertex(nxt, false);
				
				// 그 노드(subTree)가 자기 자신 이하에 있다면 절단점 
//				System.out.println("----- " + cur +" vs " + nxt);
//				System.out.println(subTree+","+discovered[cur]);
				if(!isRoot && subTree >= discovered[cur]) {
					isCutVertex[cur] = true;
				}
				ret  = Math.min(ret, subTree);
			}else {
//				System.out.println("!----- " + cur +" vs " + nxt);
				ret  = Math.min(ret, discovered[nxt]);
			}
		}
		
		// 루트인 경우 서브트리가 2개 이상인 경우만 절단점
		if(isRoot) {
			isCutVertex[cur] = child>=2;
		}
		
		// 해당 서브트리에서 가장 먼저 발견된 정점(discovered) 반환
		return ret;
	}
}

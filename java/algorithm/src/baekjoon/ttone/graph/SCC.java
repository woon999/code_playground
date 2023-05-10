package baekjoon.ttone.graph;

// #2150 graph Strongly Connected Component  - SCC 
import java.io.*;
import java.util.*;

public class SCC {
	
	static int idx, sccCnt=0;
	static int[] discovered, sccId;
	static List<Integer>[] list;
	static Stack<Integer> stack;
	static List<Queue<Integer>> result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[v+1];
		discovered = new int[v+1]; // 각 정점 발견 순서 
		sccId = new int[v+1]; // 각 정점 컴포넌트 번호  
		for(int i=1; i<v+1; i++) {
			list[i] = new ArrayList<>();
			discovered[i] = -1;
			sccId[i] = -1;
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		result = new ArrayList<>();
		stack = new Stack<>();
		for(int i=1; i<v+1; i++) {
			if(discovered[i] == -1) {
				scc(i);	
			}
		}
		
		System.out.println(sccCnt);
		Collections.sort(result, (o1,o2) -> o1.peek()-o2.peek());
		StringBuilder sb = new StringBuilder();
		for(Queue<Integer> q : result ) {
			while(!q.isEmpty())	{
				sb.append(q.poll()+" ");
			}
			sb.append(-1+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int scc(int cur) {
		discovered[cur] = idx++;
//		System.out.println(cur);
		stack.push(cur);
		
		int ret = discovered[cur];
		for(int i=0; i<list[cur].size(); i++) {
			int nxt = list[cur].get(i);
//			System.out.print(" - " + nxt);
			if(discovered[nxt]==-1) { //방문하지 않은 노드 
				ret = Math.min(ret, scc(nxt));
			} else if(sccId[nxt]==-1) { // 방문했지만 scc에 속하지 않은 노드
				ret = Math.min(ret, discovered[nxt]);
			}
		}
		
		// 역방향 간선이 부모(cur) 이하이면 scc로 묶는다. 
		if(ret == discovered[cur]) {
//			System.out.println(cur +" start ");
			Queue<Integer> q = new PriorityQueue<>();
			while(true) {
				int t = stack.pop();
//				System.out.print(t +" ");
				q.add(t);
				sccId[t] = sccCnt;
				if(t == cur) break;
			}
			result.add(q);
			sccCnt++;
		}
//		System.out.println();
		
		return ret;
	}
}

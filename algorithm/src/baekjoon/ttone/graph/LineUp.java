package baekjoon.ttone.graph;

// #2252 graph 줄 세우기 - 위상 정렬 
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class LineUp {

	static int n,m;
	static List<Integer>[] list;
	static int[] rank;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		rank = new int[n+1];
		sb = new StringBuilder();
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0; i<m; i++) {
			st=  new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			rank[b]++;
			map.put(a, map.getOrDefault(a,0));
			map.put(b, map.getOrDefault(b,0)+1);
		}
		
		for(int rank : map.values()) {
			System.out.println(rank);
			
		}
		
		List<Entry<Integer, Integer>> list_entries = new ArrayList<>(map.entrySet());

		Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2)
			{
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});
		
		for(Entry<Integer, Integer> entry : list_entries) {
			System.out.print(entry.getKey() + " ");
		}
		
		
		
		
//		bfs();
//		System.out.println(sb.toString());
	}
	
//	static void bfs() {
//		Queue<Integer> q = new LinkedList<>();
//		
//		for(int i=1; i<n+1; i++) {
//			if(rank[i] == 0) {
//				q.add(i); // 루트노드 
//			}
//		}
//			
//		while(!q.isEmpty()) {
//			int pos = q.poll();
//			
//			sb.append(pos+" ");
//			for(int next : list[pos]) {
//				rank[next]--;
//				System.out.println(next + " ," + rank[next]);
//				if(rank[next]==0) {
//					q.add(next);
//					
//				}
//			}
//		}
//		
//	}
}

package baekjoon.ttone.tree;

// #6416 tree 트리인가? 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class IsTree {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> vertex = new HashSet<>();
		Set<Integer> node = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		out : 
		for(int tc=1;;tc++) {
			map = new HashMap<>();
			vertex = new HashSet<>();
			node = new HashSet<>();
			boolean flag = false;
			
			st = new StringTokenizer(br.readLine());
			while(true) {
				if(!st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				if(a == 0) break;
				if(a== -1) break out;
				
				node.add(a);
				node.add(b);
				if(!vertex.add(b)) { // 간선 2개이상 
					flag = true;
				}
				map.put(a, map.getOrDefault(a, 0)+1);
			}
			
			if(vertex.size()!=0) {
				int rootNum=0;
				for(int num : map.keySet()) {
					if(!vertex.contains(num)) rootNum++;
				}
				
				// root 1개 이상 or 0 || 노드개수-1 != 트리 간선갯수-1
				if(rootNum!=1 || (node.size()-1 != vertex.size())) flag = true;
			}
			
			if(flag) {
				sb.append("Case " + (tc)+" is not a tree.\n");
			}else {
				sb.append("Case " + (tc)+" is a tree.\n");
			}
		}
		System.out.println(sb.toString());
	}
}

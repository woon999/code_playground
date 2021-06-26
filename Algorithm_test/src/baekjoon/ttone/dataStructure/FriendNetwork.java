package baekjoon.ttone.dataStructure;

// #4195 dataStructure 친구 네트워크 - 중간저장 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FriendNetwork {

	static int cnt;
	static int[] parents, rank;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		for(int test=0; test<t; test++) {
			int f = Integer.parseInt(br.readLine());
			
			parents = new int[2*f];
			rank = new int[2*f];
			for(int i=1; i<2*f; i++) {
				parents[i] = i;
			}
			
			int idx=1;
			
			cnt =0;
			for(int i=0; i<f; i++) {
				String line = br.readLine();
				st = new StringTokenizer(line);
				for(int j=0; j<2; j++) {
					String name = st.nextToken();
					if(!map.containsKey(name)) {
						map.put(name, idx++);
					}
				}
				
				String[] network = line.split(" ");
				int to = map.get(network[0]);
				int from = map.get(network[1]);
//				System.out.println(to +" ," + from);
				
				union(to,from);
				
				for(int p=1; p<2*f; p++) {
					System.out.print(rank[p]+" ");
				}
				System.out.println();
				
				sb.append(cnt+"\n");
			}
		}
		
		System.out.println(sb.toString());
		

	}
	
	static int find(int x) {
		if(parents[x] == x) return x;
		int rx = find(parents[x]);
		return rx;
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		System.out.println(x+","+y);
		if(rank[x] < rank[y]) { // y가 부모
			parents[x] = y;
			rank[y]++;
		}else {
			parents[y] = x;
			
			if(rank[x]== rank[y]) {
				rank[x]++;
				
				if(rank[y]!=0 && x!=y) {
					rank[x]++;
					rank[y]--;
				}
			}
		}
		
	}
	
}

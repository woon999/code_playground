package kakao.intern2021;

// #4 kakao2021intern 미로 탈출 - 중간 저장

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MazeEscape {
	
	static int INF = Integer.MAX_VALUE, answer;
	static int[][] dist;
	static boolean[][] lineStatus;
	static List<Node>[] list;
	static Map<Integer, Integer> trapList;
	
	static class Node implements Comparable<Node>{
		int to;
		int weight;
		int prevStatus;
		int status;
		
		public Node(int to, int weight, int prevStatus, int status) {
			this.to = to;
			this.weight = weight;
			this.prevStatus= prevStatus;
			this.status = status;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}

	public static void main(String[] args) {
		
//		int n = 3;
//		int start = 1;
//		int end = 3;
//		int[][] roads = {{1,2,2} , {3,2,3}};
//		int[] traps = {2};
		
		int n = 4;
		int start = 1;
		int end = 4;
		int[][] roads = {{1,2,3}, {1,2,1},  {3,2,1}, {2,4,1}};
		int[] traps = {2,3};
		
		System.out.println(solution(n, start, end, roads, traps));
	}
	
	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
		lineStatus = new boolean[n+1][n+1];
        list = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) {
        	list[i] = new ArrayList<>();
        }
        trapList = new HashMap<>();
        for(int i=0; i<traps.length; i++) {
        	trapList.put(traps[i], 1<<(i+1));
        }
        
        for(int i=0; i<roads.length; i++) {
        	int from = roads[i][0];
        	int to = roads[i][1];
        	int w = roads[i][2];
        
        	list[from].add(new Node(to,w,0,0));
        	list[to].add(new Node(from,w,0,0));
        	
        	lineStatus[from][to] = true;
        }
        
        int len = 1<<(traps.length+1);
        dist= new int[n+1][len];
        for(int i=1; i<n+1; i++) {
        	Arrays.fill(dist[i], INF);
        }
        
        dijkstra(start, end);
      
        return answer;
    }
	
	static void dijkstra(int start, int end) {
		Queue<Node> q = new PriorityQueue<>();
		
		q.add(new Node(start,0,0,0));
		dist[start][0] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;
			int w = node.weight;
			int prev = node.prevStatus;
			int status = node.status;
			
			if(to == end) {
				answer = w;
				return;
			}
			
			int check = prev^status;
			if(trapList.containsKey(to)) {
				if((check & trapList.get(to)) == trapList.get(to)) {
					for(Node c : list[to]) {
						lineStatus[to][c.to] = !lineStatus[to][c.to];
						lineStatus[c.to][to] = !lineStatus[c.to][to];
					}
				}
			}
			
			for(Node nxt : list[to]) {
				if(lineStatus[to][nxt.to]) {
					int nStatus= status;
					if(dist[nxt.to][status] >= w + nxt.weight) {
						dist[nxt.to][status] = w + nxt.weight;
						if(trapList.containsKey(nxt.to)) {
							// 다음 노드가 trap인데 활성화가 안되어있는 경우
							if((status & trapList.get(nxt.to)) == 0){
								nStatus = status|trapList.get(nxt.to);
							}
							// 다음 노드가 trap인데 활성화 되어있는 경우 
							else {
								nStatus = status^trapList.get(nxt.to);
							}
						}
						// 다음 노드가 trap이 아닌 경우는 그냥 보냄 nStatus = status;
                        
                        q.add(new Node(nxt.to, dist[nxt.to][status], status, nStatus));
					}
				}
			}
		}
	}
	
}

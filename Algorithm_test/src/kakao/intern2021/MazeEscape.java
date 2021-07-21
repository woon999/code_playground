package kakao.intern2021;

// #4 kakao2021intern 미로 탈출 - 비트마스크, 다익스트라 
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MazeEscape {
	
	static int INF = Integer.MAX_VALUE;
	static int[][] dist;
	static List<Node>[] list, rList;
	static Map<Integer, Integer> trapList;
	
	static class Node implements Comparable<Node>{
		int to;
		int weight;
		int status;
		
		public Node(int to, int weight, int status) {
			this.to = to;
			this.weight = weight;
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
		int[][] roads = {{1,2,1}, {1,2,3}, {3,2,1}, {2,4,1}};
		int[] traps = {2,3};
		
		System.out.println(solution(n, start, end, roads, traps));
	}
	
	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
	        list = new ArrayList[n+1];
	        rList = new ArrayList[n+1];
	        for(int i=1; i<n+1; i++) {
	            list[i] = new ArrayList<>();
	            rList[i] = new ArrayList<>();
	        }
	        trapList = new HashMap<>();
	        for(int i=0; i<traps.length; i++) {
	            trapList.put(traps[i], 1<<(i+1));
	        }

	        for(int i=0; i<roads.length; i++) {
	            int from = roads[i][0];
	            int to = roads[i][1];
	            int w = roads[i][2];

	            list[from].add(new Node(to,w,0));
	            rList[to].add(new Node(from,w,0));
	        }

	        int len = 1<<11;
	        dist= new int[n+1][len];
	        for(int i=1; i<n+1; i++) {
	            Arrays.fill(dist[i], INF);
	        }

	        dijkstra(start, end);

	        int answer = INF;
	        for(int ca : dist[end]) {
	            answer = Math.min(answer, ca);
	        }

	        return answer;
	    }

	    static void dijkstra(int start, int end) {
	        Queue<Node> q = new PriorityQueue<>();

	        q.add(new Node(start,0,0));
	        dist[start][0] = 0;

	        while(!q.isEmpty()) {
	            Node node = q.poll();
	            int to = node.to;
	            int w = node.weight;
	            int status = node.status;
	            
	            if(to == end) return;

	            // f1 (pos node) :trap o  / trap x or 일반
	            boolean f1 = false; 
	            if(trapList.containsKey(to)) {
	                if((status & trapList.get(to)) == trapList.get(to)) {
	                    f1 = true;
	                }
	            }

	            // 정방향 (tt, ff) 
            	// f2(nxt node) :trap o  / trap x or 일반  
	            boolean f2 = false;
	            for(Node nxt : list[to]) {
                    int nStatus= status;
                    if(trapList.containsKey(nxt.to)) {
                    	f2 = ((status & trapList.get(nxt.to)) != 0);
                    	nStatus = trapSwitch(f2, status,trapList.get(nxt.to));
	                    
	                    if(f1&f2 || (!f1&!f2)) {
	                    	if(dist[nxt.to][status] > w + nxt.weight) {
                                dist[nxt.to][status] = w + nxt.weight;
                                q.add(new Node(nxt.to, dist[nxt.to][status], nStatus));
                            }
	                    }
                    }
                    else {
                        if(!f1) {
                            if(dist[nxt.to][status] > w + nxt.weight) {
                                dist[nxt.to][status] = w + nxt.weight;
                                q.add(new Node(nxt.to, dist[nxt.to][status], nStatus));
                            }
                        }
                    }
                   
	            }
	            // 역방향 (tf, ft)  
	            f2 = false;
	            for(Node nxt : rList[to]) {
	                int nStatus= status;
	                if(trapList.containsKey(nxt.to)) {
	                	f2 = ((status & trapList.get(nxt.to)) != 0);
	                	nStatus = trapSwitch(f2, status,trapList.get(nxt.to));
	                }
	                if(f1^f2) {
                    	if(dist[nxt.to][status] > w + nxt.weight) {
                            dist[nxt.to][status] = w + nxt.weight;
                            q.add(new Node(nxt.to, dist[nxt.to][status], nStatus));
                        }
                    }
	            }
	        }
	    }
	    static int trapSwitch(boolean flag, int now, int node ) {
			// 다음 노드가 trap인데 활성화 되어있는 경우 
			if(flag) {
				// 1110
				// 0010
				// 1100
				return now^node;
			}
			// 다음 노드가 trap인데 활성화 되어있지 않은 경우 
			else {
				// 1100
				// 0010
				// 1110
				return now|node;
			}
		}

}

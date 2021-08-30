package kakao.blind2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindRouteGame {

	static class Node implements Comparable<Node>{
		int idx;
		int x;
		int y;
		
		public Node(int idx, int x, int y){
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.y-this.y;
		}
	}
	public static void main(String[] args) {
		int[][] nodeinfo = {
			{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}
		};
		
		solution(nodeinfo);
	}
	
	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};
        int size = nodeinfo.length;
        
        List<Node> list = new ArrayList<>();
        
        for(int i=0; i<size; i++) {
        	list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(list);
        // 중위 순회 
        int rootIdx = list.get(0).idx;
        int treeH = list.get(0).y;
        System.out.println(rootIdx);
        
        for(int i=0; i<size-1; i++) {
        	Node pos = list.get(i);
        	System.out.println((pos.idx) + " : " +pos.x+","+pos.y);
        	
        	for(int j=i+1; j<size; j++) {
        		Node nxt = list.get(j);
        		if(pos.x < nxt.x) {
        			
        		}
        	}
        }
        
        
        
        
       
        return answer;
    }
	static void find(int pos) {
		
	}
}

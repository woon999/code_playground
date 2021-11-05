package kakao.blind2019;

// blind #5 길 찾기 게임 - 트리 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindRouteGame {

	static int preIdx =0, postIdx=0; 
	static int[][] gnode_info, answer;
	static List<Node> nodeData;
	static class Node implements Comparable<Node>{
		int idx;
		int x;
		int y;
		Node left;
		Node right;
		
		public Node(int idx, int x, int y){
			this.idx = idx;
			this.x = x;
			this.y = y;
			left = null;
			right =null;
		}
		
		public void insertLeft(Node leafNode) {
			this.left = leafNode;
		}
		
		public void insertRight(Node leafNode) {
			this.right = leafNode;
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
        int size = nodeinfo.length;
        answer = new int[2][size];
        gnode_info = nodeinfo;
        nodeData = new ArrayList<>();
        for(int i=1; i<size+1; i++) {
        	nodeData.add(new Node(i, nodeinfo[i-1][0], nodeinfo[i-1][1]));
        }
        
        Collections.sort(nodeData);
        Node root = nodeData.get(0);
        if(size>1) {
        	int nxt =1, nh =0;
	        while(true) {
	        	nh = nodeData.get(nxt).y;
				while(nxt < nodeData.size()) {
					if(nh == nodeData.get(nxt).y) {
						initNode(root, nodeData.get(nxt++));
					}else break;
				}
				if(nxt > nodeData.size()-1) break;
	        }
        }
        
        traversal(root);
        
        for(int i=0; i<2; i++) {
        	for(int j=0; j<size; j++) {
        		System.out.print(answer[i][j]+" ");
        	}
        	System.out.println();
        }
        
        return answer;
    }
	
	static void traversal(Node cur) {
		if(cur==null) return;
		answer[0][preIdx++] = cur.idx;
		traversal(cur.left);
		traversal(cur.right);
		answer[1][postIdx++] = cur.idx;
	}
	
	static void initNode(Node cur, Node child) {
		if(cur.x > child.x) {
			if(cur.left == null ) {
				cur.insertLeft(child);
			}else {
				initNode(cur.left, child);
			}
		}else {
			if(cur.right == null ) {
				cur.insertRight(child);
			}else {
				initNode(cur.right, child);
			}
		}
	}
}
